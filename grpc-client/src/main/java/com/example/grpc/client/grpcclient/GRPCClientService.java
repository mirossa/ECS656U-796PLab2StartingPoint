package com.example.grpc.client.grpcclient;

import com.example.grpc.server.grpcserver.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GRPCClientService {
	//list of ip addresses
	String [] ip = new String[] {"localhost"};//{"10.182.0.8","10.182.0.9","10.182.0.10","10.182.0.11","10.182.0.12","10.182.0.4","10.182.0.6","10.182.0.16"};
	//channels
	ManagedChannel[] channel = new ManagedChannel[ip.length];
	//stubs
	MatrixServiceGrpc.MatrixServiceBlockingStub[] stub = new MatrixServiceGrpc.MatrixServiceBlockingStub[ip.length];
	//index of the next server which the add/multiply operation should be executed on.
	int current_server = 0 ;

	//initialise channels and stubs
	public void initialise(){
		for (int i=0;i<ip.length;i++){
			channel[i] = ManagedChannelBuilder.forAddress(ip[i], 9090).usePlaintext().build();
			stub[i] = MatrixServiceGrpc.newBlockingStub(channel[i]);
		}
	}
	// ping server (unchanged)
	public String ping() {
        	ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
		PingPongServiceGrpc.PingPongServiceBlockingStub stub
                = PingPongServiceGrpc.newBlockingStub(channel);
		PongResponse helloResponse = stub.ping(PingRequest.newBuilder()
                .setPing("")
                .build());
		channel.shutdown();
		return helloResponse.getPong();
    }
	// add 2 matrices (unchanged)
    public String add(){
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090)
		.usePlaintext()
		.build();
		MatrixServiceGrpc.MatrixServiceBlockingStub stub
		 = MatrixServiceGrpc.newBlockingStub(channel);
		MatrixReply A=stub.addBlock(MatrixRequest.newBuilder()
			.setA00(1)
			.setA01(2)
			.setA10(5)
			.setA11(6)
			.setB00(1)
			.setB01(2)
			.setB10(5)
			.setB11(6)
			.build());
		return A.getC00()+" "+A.getC01()+"<br>"+A.getC10()+" "+A.getC11()+"\n";
    }
	// multiply 2 matrices (unchanged)
    public String multiply(){
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090)
		.usePlaintext()
		.build();
		MatrixServiceGrpc.MatrixServiceBlockingStub stub
		 = MatrixServiceGrpc.newBlockingStub(channel);
		MatrixReply A=stub.addBlock(MatrixRequest.newBuilder()
			.setA00(1)
			.setA01(2)
			.setA10(3)
			.setA11(4)
			.setB00(1)
			.setB01(2)
			.setB10(3)
			.setB11(4)
			.build());
		return A.getC00()+" "+A.getC01()+"<br>"+A.getC10()+" "+A.getC11()+"\n";
	}
	// process 2 input matrix files,
	public String matAddOrMultiply(String s1, String s2, String operation) throws BadMatrixException{
		//print matrices
		System.out.println("matrix 1:\n"+s1);
		System.out.println("matrix 2:\n"+s2);
		//empty check
		if (s1.length()!=0 && s2.length()!=0) {
			//parse input strings
			int[][] m1 = str2Array(s1);
			int[][] m2 = str2Array(s2);
			//check that matrices are a power of 2 & dimensions are equal
			if (powerOf2(m1) && powerOf2(m2) && m1.length == m2.length) {
				if (Objects.equals(operation, "multiply")) {
					//setup channels & stubs
					initialise();
					//dimensions
					int n = m1.length;
					//generate random number
					Random rand = new Random();
					int randNum = rand.nextInt(ip.length);
					//calculate time taken (in milliseconds)
					int footprint = (int) footprint(stub[randNum]);
					// estimate the total runtime
					float total_runtime = (float) (n * footprint);
					// deadline
					float deadline = 200;// in milliseconds
					// calculate number of required servers.
					// if the number of servers required > the number of servers available then use all servers.
					int serversrequired = Math.min((int) Math.ceil(total_runtime/deadline), ip.length);
					if(serversrequired==8) System.out.println("warning: the ");
					System.out.println(serversrequired + " servers needed");
					//System.out.println("total runtime: "+total_runtime+"ms");

					//dot product result
					int[][] C = multiplication(m1, m2);
					//print matrix product
					System.out.println("product:\n"+array2String(C));
					return array2String(C);//convert to string and return

				}else if (Objects.equals(operation, "add")){
					//note: deadline-based scaling is not performed for addition.
					//result
					initialise();
					int[][] C = addition(m1, m2);
					System.out.println("sum:\n"+array2String(C));

					return array2String(C);
				} else throw new IllegalArgumentException("Error: invalid operator. The operator must be either 'add' or 'multiply'.");
			}
			else throw new BadMatrixException("Error: wrong dimensions. either: a) one/both of the matrix dimensions are not a power of 2 and/or b) the dimensions of the 2 matrices are not equal.");
		} else {
			throw new BadMatrixException("Error: Either one or both of the input matrices are empty.");
		}
	}
	//convert array to string with each row on a new line
	public String array2String (int[][] x){
		StringBuilder a = new StringBuilder();
		StringBuilder row;
		for(int[] i:x){
			row = new StringBuilder();
			for(int j:i) {
				row.append(j).append(" ");
			}
			a.append(row).append("\n");
		}
		return a.toString();
	}

	//convert input string to array
	public int[][] str2Array (String str) throws BadMatrixException {
		//split into matrix into rows
		String[] s1  = str.split("\\r?\\n");
		//check that the matrix is square
		int rows = s1.length;
		for (String i:s1){
			int cols = i.split(" ").length;
			if (rows != cols) throw new BadMatrixException("Error: The matrix is not square. Check the matrix files.");
		}
		//parse string to int
		try{
			int[][] m1 = new int[rows][rows];
			for (int i=0; i<s1.length;i++){
				String[] r = s1[i].split(" ");
				for (int j=0; j<r.length;j++){
					m1 [i][j] = Integer.parseInt(r[j]);
				}
			}
			return m1;
		}catch(Exception e){
			throw new BadMatrixException("Error: Could not parse matrices. Check that there are no invalid characters in the files.");
		}
	}

	//power of 2
	static boolean powerOf2(int[][] i){
		int n = i[0].length;
		if (n<=0) return false;
		while(n!=1){
			if(n % 2 != 0) return false;
			n = n/2;
		}
		return true;
	}

	// call the multiplyBlock() server function and return the response
	public int[][] dotProduct(int[][] m1 , int[][] m2) {
		//select the next available stub
		MatrixServiceGrpc.MatrixServiceBlockingStub stub1 = this.stub[current_server];
		current_server = (current_server +1) % ip.length;// iterate the stub index
		MatrixReply A = stub1.multiplyBlock(MatrixRequest.newBuilder()
				.setA00(m1[0][0])
				.setA01(m1[0][1])
				.setA10(m1[1][0])
				.setA11(m1[1][1])
				.setB00(m2[0][0])
				.setB01(m2[0][1])
				.setB10(m2[1][0])
				.setB11(m2[1][1])
				.build());

		return new int[][]{{A.getC00(), A.getC01()}, {A.getC10(), A.getC11()}};
	}

	// call the addBlock() server function and return the response
	public int[][] matrixAdd(int[][] m1, int[][] m2){
		//select the next available stub
		MatrixServiceGrpc.MatrixServiceBlockingStub stub1 = this.stub[current_server];
		current_server = (current_server +1) % ip.length;// iterate the stub index
		//server reply
		MatrixReply A = stub1.addBlock(MatrixRequest.newBuilder()
				.setA00(m1[0][0])
				.setA01(m1[0][1])
				.setA10(m1[1][0])
				.setA11(m1[1][1])
				.setB00(m2[0][0])
				.setB01(m2[0][1])
				.setB10(m2[1][0])
				.setB11(m2[1][1])
				.build());
		return new int[][] {{A.getC00(), A.getC01()}, {A.getC10(), A.getC11()}};
	}

	// calculate the dot product of two NxN matrices
	public int[][] multiplication(int[][] x, int[][] y){
		int[][][] product ;//product
		// scalar multiplication, only used if input is a scalar
		if (x.length ==1) return new int[][] {{x[0][0]*y[0][0]}};
		// call the multBlock() server function if the inputs have 2x2 dimensions
		if (x.length ==2) return dotProduct(x,y);
			//if the matrix is bigger than 2x2, divide each matrix into 4 blocks and
			// calculate the dot product of each block recursively.
		else {
			// divide the matrix into 4 blocks
			int [][][] m1=disassemble(x);
			int [][][] m2=disassemble(y);
			product = new int[m1.length][][];
			for(int i =0;i<4;i++){
				//  calculate the dot product of each of the 4 quadrants:
				//  ---------       ---------       -----------------------------
				//  | a | b |       | e | f |       | (a*e)+(b*g) | (a*f)+(b*h) |
				//  ---------   *   ---------   =   -----------------------------
				//  | c | d |       | g | h |       | (c*e)+(d*g) | (c*f)+(d*h) |
				//  ---------       ---------       -----------------------------

				int[][] a = m1[0];
				int[][] b = m1[1];
				int[][] c = m1[2];
				int[][] d = m1[3];
				int[][] e = m2[0];
				int[][] f = m2[1];
				int[][] g = m2[2];
				int[][] h = m2[3];

				product[0] = addition(multiplication(a,e), multiplication(b,g));
				product[1] = addition(multiplication(a,f), multiplication(b,h));
				product[2] = addition(multiplication(c,e), multiplication(d,g));
				product[3] = addition(multiplication(c,f), multiplication(d,h));
			}
			// combine the 4 quadrants back into 1 matrix
			return reassemble(product);
		}
	}
	// calculate the sum of two NxN matrices
	public int[][] addition(int[][]x, int[][]y){
		int [][][] result ;
		int [][] sum;
		if (x.length ==1) return new int[][] {{x[0][0],y[0][0]}};
		//make a server request
		if (x.length ==2) {
			sum = matrixAdd(x,y);
		}
		else {
			//divide into 4
			int [][][] m1 = disassemble(x);
			int [][][] m2 = disassemble(y);
			result = new int[m1.length][][];
			for(int i =0;i<4;i++){
				result[i] = addition(m1[i],m2[i]);
			}
			sum = reassemble(result);
		}
		return sum;
	}


	//divide an NxN matrix into 4 blocks
	public int [][][] disassemble(int [][] a){
		int row = a.length/2;
		int rows = 2;
		ArrayList<int[][]> divided = new ArrayList<>();
		int counterRow = 0;
		int counterColumn = 0;
		for (int i = 0; i < rows; i++) {
			loops:
			for (int j = 0; j < rows; j++) {
				int[][] copyArray = new int[row][row];
				for (int k = 0; k < row; k++) {
					if (counterRow>=row*2 || counterColumn> row) break loops;
					System.arraycopy(a[counterRow], counterColumn, copyArray[k], 0, row);
					counterRow++;
				}
				divided.add(copyArray);
			}
			counterRow = 0;
			counterColumn += row;
		}
		int n = divided.size();
		int[][][] divided_blocks = new int [n][][];
		for (int i=0;i<n;i++){
			divided_blocks[i] = divided.get(i);
		}
		int[][][] k = new int[divided_blocks.length][][];
		k[0] = divided_blocks[0];
		k[1] = divided_blocks[2];
		k[2] = divided_blocks[1];
		k[3] = divided_blocks[3];

		return k;
	}
	//combine 4 blocks into 1 big matrix
	public int[][] reassemble(int [][][] x){
		int n = x[0].length;
		int[][] out = new int [n*2][2*n];
		int count = 0;
		int count2 = 0;
		do {
			int j = 0;
			while (j < n) {
				System.arraycopy(x[count][j], 0, out[count2], 0, n);
				System.arraycopy(x[count + 1][j], 0, out[count2], n, n);
				count2++;
				j++;
			}
			count += 2;
		} while (count2 < n * 2);
		return out;
	}

	//calculate the time taken a multiplyBlock() server operation to complete
	public long footprint(MatrixServiceGrpc.MatrixServiceBlockingStub fpStub) {
		long tick = System.currentTimeMillis();//start time
		MatrixReply A = fpStub.multiplyBlock(MatrixRequest.newBuilder().setA00(1).setA11(1).setB00(1).setB11(1).build());//server function call
		long tock = System.currentTimeMillis();//end time
		return tock-tick;
	}
}