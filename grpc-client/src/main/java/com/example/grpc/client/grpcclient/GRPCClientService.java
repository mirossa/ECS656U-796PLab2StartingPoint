package com.example.grpc.client.grpcclient;

import com.example.grpc.server.grpcserver.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class GRPCClientService {

	String [] ip = new String[] {"localhost","localhost","localhost","localhost","localhost","localhost","localhost","localhost"};//"0.0.0.0","0.0.0.0","0.0.0.0","0.0.0.0","0.0.0.0","0.0.0.0","0.0.0.0","0.0.0.0"

	ManagedChannel[] channel = new ManagedChannel[ip.length];
	MatrixServiceGrpc.MatrixServiceBlockingStub[] stub = new MatrixServiceGrpc.MatrixServiceBlockingStub[ip.length];
	int current_server = 0 ;

	//initialise channels and stubs
	public void setup(){
		for (int i=0;i<ip.length;i++){
			channel[i] = ManagedChannelBuilder.forAddress(ip[i], 9090).usePlaintext().build();
			stub[i] = MatrixServiceGrpc.newBlockingStub(channel[i]);
		}
	}

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
	public String matAddOrMultiply(String s1, String s2, String operation) throws BadMatrixException{
		//print matrices
		System.out.println("matrix 1:\n"+s1);
		System.out.println("matrix 2:\n"+s2);
		//empty check
		if (s1.length()!=0 && s2.length()!=0) {
			//parse input strings
			int[][] m1 = str2Array(s1);
			int[][] m2 = str2Array(s2);
			//check that matrices are a power of two & dimensions are equal
			if (powerOf2(m1) && powerOf2(m2) && m1.length == m2.length) {
				if (Objects.equals(operation, "multiply")) {
					//setup channnels & stubs
					setup();
					int n = m1.length;
					//generate random number
					Random rand = new Random();
					int randNum = rand.nextInt(ip.length);
					//calculate time taken (in milliseconds)
					int footprint = (int) footprint(stub[randNum]);
					//estimate the total runtime
					float total_runtime = (float) (n * footprint);
					// deadline
					float deadline = 200;// in miliseconds
					// calculate number of required servers.
					// if the number of servers required > the number of servers available then use all servers.
					int serversrequired = Math.min((int) Math.ceil(total_runtime/deadline), ip.length);

					System.out.println(serversrequired + " servers needed");
					System.out.println("total runtime: "+total_runtime+"ms");

					//dot product result
					int[][] C = multiplication(m1, m2);

					return array2String(C);//convert to string

				}else if (Objects.equals(operation, "add")){
					int[][] C = addition(m1, m2);
					//System.out.println("sum:\n"+array2String(C));

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
			throw new BadMatrixException("Error: Could not parse matrices. Check that there are no invalid characters in the files." + e);
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

	//
	public int[][] dotProduct(int[][]x, int[][]y){
		int[][] a = dotProduct(x,y, current_server);
		current_server = (current_server +1) % ip.length;// cycle through each stub
		return a;
	}

	public int[][] dotProduct(int[][] m1 , int[][] m2, int i) {
		MatrixServiceGrpc.MatrixServiceBlockingStub stub = this.stub[i];

		MatrixReply A = stub.multiplyBlock(MatrixRequest.newBuilder()
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

	public int[][] matrixAdd(int[][]x, int[][]y){
		int[][] a = matrixAdd(x,y, current_server);
		current_server = (current_server +1) % ip.length;// cycle through each stub
		return a;
	}
	public int[][] matrixAdd(int[][] m1, int[][] m2, int i){
		MatrixServiceGrpc.MatrixServiceBlockingStub stub = this.stub[i];

		MatrixReply A = stub.addBlock(MatrixRequest.newBuilder()
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

	//divide the matrix into 4 recursively and calculate the dot product for each 2x2 matrix using the server
	public int[][] multiplication(int[][]x, int[][]y){
		int[][][] C ;

		if (x.length ==2)
			return dotProduct(x,y);
		else {
			//divide each matrix into 4 blocks
			int [][][] m1=disassemble(x,2);
			int [][][] m2=disassemble(y,2);
			C = new int[m1.length][][];
			for(int i =0;i<m1.length;i++){

				int[][] a = m1[0];
				int[][] b = m1[1];
				int[][] c = m1[2];
				int[][] d = m1[3];
				int[][] e = m2[0];
				int[][] f = m2[1];
				int[][] g = m2[2];
				int[][] h = m2[3];

				C[0] = addition(multiplication(a,e), multiplication(b,g));
				C[1] = addition(multiplication(a,f), multiplication(b,h));
				C[2] = addition(multiplication(c,e), multiplication(d,g));
				C[3] = addition(multiplication(c,f), multiplication(d,h));//4 *12 *12

				//System.out.println("r "+Arrays.deepToString(p));
				if(i>=3)break;
			}
			return reassemble(C);
		}
	}
	//divide the matrix into 4 recursively and calculate the sum of each 2x2 matrix using the server
	public int[][] addition(int[][]x, int[][]y){
		int[][][] p ;
		int [][] q;
		//make a server request
		if (x.length ==2) q = matrixAdd(x,y);
		else {
			//divide into 4
			int [][][] m1=disassemble(x,2);
			int [][][] m2=disassemble(y,2);
			p = new int[m1.length][][];
			for(int i =0;i<m1.length;i++){
				//if(x.length==8) System.out.println("x "+ x.length);
				//if(x.length==8) System.out.println("i "+ i);
				p[i] = addition(m1[i],m2[i]);
				//System.out.println("r "+Arrays.deepToString(p));
				if(i>=3)break;
			}
			//if(x.length==8) System.out.println("here???");
			q=reassemble(p);
		}

		//if(x.length==8) System.out.println("here???");
		return q;
	}

	//divide an NxN matrix into 4 sub-matrices
	public int [][][] disassemble(int [][] a, int rows){
		int row = a.length/2;
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
		k[0]=divided_blocks[0];
		k[1]=divided_blocks[2];
		k[2]=divided_blocks[1];
		k[3]=divided_blocks[3];

		//System.out.println("qqq "+Arrays.deepToString(k));
		return k;
	}
	//combine 4 sub-matrices into 1 big matrix
	public int[][] reassemble(int [][][] x){
		int n = x[0].length;
		int[][] out = new int [n*2][2*n];

		int count = 0;
		int count2 = 0;
		do {
			int j = 0;
			while (j < n) {
				//System.out.println(count);
				System.arraycopy(x[count][j], 0, out[count2], 0, n);
				System.arraycopy(x[count + 1][j], 0, out[count2], n, n);
				count2++;
				j++;
			}
			count += 2;
		} while (count2 < n * 2);
		return out;
	}

	//calculate time taken for multiply operation to complete
	public long footprint(MatrixServiceGrpc.MatrixServiceBlockingStub fpStub) {
		long tick = System.currentTimeMillis();//start time
		MatrixReply A = fpStub.multiplyBlock(MatrixRequest.newBuilder().setA00(1).setB00(1).build());//server function call
		long tock = System.currentTimeMillis();//end time
		return tock-tick;
	}





}

