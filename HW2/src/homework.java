   import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.Arrays;
	import java.util.HashMap;
public class homework {

	public static void main(String args[]){
		
		BufferedReader br=null;
		String file="input.txt";
		Game game=null;
		char opponent_play;
		double result=Double.NEGATIVE_INFINITY;
		double resultCopy=result;
		String move = null;
		String pos=null;
		HashMap<Integer, String>alphabet=new HashMap<Integer,String>();
		alphabet.put(0, "A");
		alphabet.put(1, "B");
		alphabet.put(2, "C");
		alphabet.put(3, "D");
		alphabet.put(4, "E");
		alphabet.put(5, "F");
		alphabet.put(6, "G");
		alphabet.put(7, "H");
		alphabet.put(8, "I");
		alphabet.put(9, "J");
		alphabet.put(10, "K");
		alphabet.put(11, "L");
		alphabet.put(12, "M");
		alphabet.put(13, "N");
		alphabet.put(14, "O");
		alphabet.put(15, "P");
		alphabet.put(16, "Q");
		alphabet.put(17, "R");
		alphabet.put(18, "S");
		alphabet.put(19, "T");
		alphabet.put(20, "U");
		alphabet.put(21, "V");
		alphabet.put(22, "W");
		alphabet.put(23, "X");
		alphabet.put(24, "Y");
		alphabet.put(25, "Z");
		


		try {
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			//File writeFile= new File("output.txt");
			String line;
			game=new Game();
			String N= br.readLine().trim();
			String Mode=br.readLine().trim();
			String YouPlay=br.readLine().trim();
			String Depth=br.readLine().trim();
			game.config=Integer.parseInt(N);
			game.mode=Mode;
			game.youPlay=YouPlay;
			game.depth=Integer.parseInt(Depth);
			game.cell_values=new String[game.config][game.config];
			game.board_state=new char[game.config][game.config];
			for(int i=0;i<Integer.parseInt(N);i++){
			String statesInfo=br.readLine();
			String[] statesSplit=statesInfo.split(" ");
			int j=0;
			String movetype;
			
			for(String f:statesSplit){

				game.cell_values[i][j]=f;
				
				j++;
			}
			}
			for(int i=0;i<Integer.parseInt(N);i++){
				String statesInfo=br.readLine();
				char[] statesSplit=statesInfo.toCharArray();
				int j=0;
				for(char f:statesSplit){
					game.board_state[i][j]=f;
					j++;
				}
				}
			
			
	}
		catch (FileNotFoundException e) {

			System.out.println("File not found");

		} catch (IOException e) {
			System.out.println("unable to read");
		} catch (NullPointerException ex) {
			File writeFile= new File("output.txt");
			BufferedWriter bw=null;
			try {
				bw = new BufferedWriter(new FileWriter(writeFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} finally {
			try {
				br.close();
			} catch (IOException ex) {
				System.out.println("Unable to close the file");
			} catch (NullPointerException ex) {

			}
		}
		if(game.youPlay.charAt(0)=='X'){
			opponent_play='O';
			game.opponent_play='O';
			
		}
		else
		{
			opponent_play='X';
			game.opponent_play='X';
		}
	if(game.mode.equals("MINIMAX")){	
		for(int i=0;i<game.config;i++){
			for(int j=0;j<game.config;j++){
				if(game.board_state[i][j]=='.'){
					char[][] state=new char[game.config][game.config];
					for(int m=0;m<game.config;m++){
						for(int n=0;n<game.config;n++){
							state[m][n]=game.board_state[m][n];
						}
					}
					

					state[i][j]=game.youPlay.charAt(0);
					
					double returnval=minimax(state,game,opponent_play,1,i,j,"Stake",game.youPlay.charAt(0));
					for(int m=0;m<game.config;m++){
						for(int n=0;n<game.config;n++){
							state[m][n]=game.board_state[m][n];
						}
					}
					//System.out.println();
					//state=game.board_state.clone();
					//System.out.println("state is "+state[0][0]);
					
					//System.out.println(returnval);
					result=Math.max(result, returnval);
					/*if(i==0 && j==0){
						System.out.println(result);
					}
					if(i==2 && j==1){
						System.out.println(result);
					}*/
					if(resultCopy!=result){
						 pos=i+" "+j;
						 move="Stake";
					}
					resultCopy=result;

					
				}
			}
		}////
		
		for(int i=0;i<game.config;i++){
			for(int j=0;j<game.config;j++){
				if(game.board_state[i][j]==game.youPlay.charAt(0)){
					char[][] state=new char[game.config][game.config];
					for(int m=0;m<game.config;m++){
						for(int n=0;n<game.config;n++){
							state[m][n]=game.board_state[m][n];
						}
					}
					if(i+1<game.config&&state[i][j]==game.youPlay.charAt(0)&&state[i+1][j]=='.'){
						state[i+1][j]=game.youPlay.charAt(0);
						double returnval=minimax(state,game,opponent_play,1,i+1,j,"Raid",game.youPlay.charAt(0));

						result=Math.max(result, returnval);
						if(resultCopy!=result){
							 pos=(i+1)+" "+j;
							 move="Raid";
						}
						resultCopy=result;
						for(int m=0;m<game.config;m++){
							for(int n=0;n<game.config;n++){
								state[m][n]=game.board_state[m][n];
							}
						}

					}
					if(i-1>=0&&state[i][j]==game.youPlay.charAt(0)&&state[i-1][j]=='.'){
						state[i-1][j]=game.youPlay.charAt(0);
						double returnval=minimax(state,game,opponent_play,1,i-1,j,"Raid",game.youPlay.charAt(0));
						result=Math.max(result, returnval);
						if(resultCopy!=result){
							 pos=(i-1)+" "+j;
							 move="Raid";
						}
						resultCopy=result;
						for(int m=0;m<game.config;m++){
							for(int n=0;n<game.config;n++){
								state[m][n]=game.board_state[m][n];
							}
						}
					}
					if(j+1<game.config&&state[i][j]==game.youPlay.charAt(0)&&state[i][j+1]=='.'){
						state[i][j+1]=game.youPlay.charAt(0);
						double returnval=minimax(state,game,opponent_play,1,i,j+1,"Raid",game.youPlay.charAt(0));
						result=Math.max(result, returnval);
						if(resultCopy!=result){
							 pos=i+" "+(j+1);
							 move="Raid";
						}
						resultCopy=result;
						for(int m=0;m<game.config;m++){
							for(int n=0;n<game.config;n++){
								state[m][n]=game.board_state[m][n];
							}
						}
					}
					if(j-1>=0&&state[i][j]==game.youPlay.charAt(0)&&state[i][j-1]=='.'){
						state[i][j-1]=game.youPlay.charAt(0);
						double returnval=minimax(state,game,opponent_play,1,i,j-1,"Raid",game.youPlay.charAt(0));
						result=Math.max(result, returnval);
						if(resultCopy!=result){
							 pos=i+" "+(j-1);
							 move="Raid";
						}
						resultCopy=result;
						for(int m=0;m<game.config;m++){
							for(int n=0;n<game.config;n++){
								state[m][n]=game.board_state[m][n];
							}
						}
					}

					//state[i][j]=game.youPlay.charAt(0);
					
					//double returnval=minimax(state,game,opponent_play,1,i,j,"Stake");
					for(int m=0;m<game.config;m++){
						for(int n=0;n<game.config;n++){
							state[m][n]=game.board_state[m][n];
						}
					}
					//state=game.board_state.clone();
					//System.out.println("state is "+state[0][0]);
					
					//System.out.println(returnval);
					//result=Math.max(result, returnval);

					
				}
			}
		}
		//System.out.println(result + pos);
	}


	/***** line of demarcation *****/
	else {
		for(int i=0;i<game.config;i++){
			for(int j=0;j<game.config;j++){
				if(game.board_state[i][j]=='.'){
					char[][] state=new char[game.config][game.config];
					for(int m=0;m<game.config;m++){
						for(int n=0;n<game.config;n++){
							state[m][n]=game.board_state[m][n];
						}
					}
					

					state[i][j]=game.youPlay.charAt(0);
					
					double returnval=minimaxab(state,game,opponent_play,1,i,j,"Stake",game.youPlay.charAt(0),Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
					for(int m=0;m<game.config;m++){
						for(int n=0;n<game.config;n++){
							state[m][n]=game.board_state[m][n];
						}
					}
					//state=game.board_state.clone();
					//.out.println("state is "+state[0][0]);
					
					//System.out.println(returnval);
					result=Math.max(result, returnval);
					if(resultCopy!=result){
						 pos=i+" "+j;
						 move="Stake";
					}
					resultCopy=result;

					
				}
			}
		}////
		
		for(int i=0;i<game.config;i++){
			for(int j=0;j<game.config;j++){
				if(game.board_state[i][j]==game.youPlay.charAt(0)){
					char[][] state=new char[game.config][game.config];
					for(int m=0;m<game.config;m++){
						for(int n=0;n<game.config;n++){
							state[m][n]=game.board_state[m][n];
						}
					}
					if(i+1<game.config&&state[i][j]==game.youPlay.charAt(0)&&state[i+1][j]=='.'){
						state[i+1][j]=game.youPlay.charAt(0);
						double returnval=minimaxab(state,game,opponent_play,1,i+1,j,"Raid",game.youPlay.charAt(0),Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);

						result=Math.max(result, returnval);
						if(resultCopy!=result){
							 pos=(i+1)+" "+j;
							 move="Raid";
						}
						resultCopy=result;
						for(int m=0;m<game.config;m++){
							for(int n=0;n<game.config;n++){
								state[m][n]=game.board_state[m][n];
							}
						}

					}
					if(i-1>=0&&state[i][j]==game.youPlay.charAt(0)&&state[i-1][j]=='.'){
						state[i-1][j]=game.youPlay.charAt(0);
						double returnval=minimaxab(state,game,opponent_play,1,i-1,j,"Raid",game.youPlay.charAt(0),Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
						result=Math.max(result, returnval);
						if(resultCopy!=result){
							 pos=(i-1)+" "+j;
							 move="Raid";
						}
						resultCopy=result;
						for(int m=0;m<game.config;m++){
							for(int n=0;n<game.config;n++){
								state[m][n]=game.board_state[m][n];
							}
						}
					}
					if(j+1<game.config&&state[i][j]==game.youPlay.charAt(0)&&state[i][j+1]=='.'){
						state[i][j+1]=game.youPlay.charAt(0);
						double returnval=minimaxab(state,game,opponent_play,1,i,j+1,"Raid",game.youPlay.charAt(0),Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
						result=Math.max(result, returnval);
						if(resultCopy!=result){
							 pos=i+" "+(j+1);
							 move="Raid";
						}
						resultCopy=result;
						for(int m=0;m<game.config;m++){
							for(int n=0;n<game.config;n++){
								state[m][n]=game.board_state[m][n];
							}
						}
					}
					if(j-1>=0&&state[i][j]==game.youPlay.charAt(0)&&state[i][j-1]=='.'){
						state[i][j-1]=game.youPlay.charAt(0);
						double returnval=minimaxab(state,game,opponent_play,1,i,j-1,"Raid",game.youPlay.charAt(0),Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
						result=Math.max(result, returnval);
						if(resultCopy!=result){
							 pos=i+" "+(j-1);
							 move="Raid";
						}
						resultCopy=result;
						for(int m=0;m<game.config;m++){
							for(int n=0;n<game.config;n++){
								state[m][n]=game.board_state[m][n];
							}
						}
					}

					//state[i][j]=game.youPlay.charAt(0);
					
					//double returnval=minimax(state,game,opponent_play,1,i,j,"Stake");
					for(int m=0;m<game.config;m++){
						for(int n=0;n<game.config;n++){
							state[m][n]=game.board_state[m][n];
						}
					}
					//state=game.board_state.clone();
					//System.out.println("state is "+state[0][0]);
					
					//System.out.println(returnval);
					//result=Math.max(result, returnval);

					
				}
			}
		}
		//System.out.println(result + pos);
		
		
		
	}
	File writeFile= new File("output.txt");
	try {
		String[] resultQueue;
		
		BufferedWriter bw=new BufferedWriter(new FileWriter(writeFile));
		String[] position=pos.split(" ");
		bw.write((alphabet.get(Integer.parseInt(position[1]))));
		int sum=Integer.parseInt(position[0])+1;
		//System.out.println("sum is"+sum);
		bw.write(sum+"");
		bw.write(' ');
		bw.write(move);
		bw.newLine();
		char[][] state1=new char[game.config][game.config];
		for(int f=0;f<game.config;f++){
			for(int g=0;g<game.config;g++){
				state1[f][g]=game.board_state[f][g];
			}
		}
		for(int f=0;f<game.config;f++){
			for(int g=0;g<game.config;g++){
				if(f==Integer.parseInt(position[0])&&g==Integer.parseInt(position[1])){
				state1[f][g]=game.youPlay.charAt(0);
				if(f+1<game.config&&state1[f+1][g]==opponent_play){
					state1[f+1][g]=game.youPlay.charAt(0);
				}
				if(f-1>=0&&state1[f-1][g]==opponent_play){
					state1[f-1][g]=game.youPlay.charAt(0);;
				}
				if(g+1<game.config&&state1[f][g+1]==opponent_play){
					state1[f][g+1]=game.youPlay.charAt(0);;
				}
				if(g-1>=0&&state1[f][g-1]==opponent_play){
					state1[f][g-1]=game.youPlay.charAt(0);;
				}
				}
				
			}
			
			
		}
		for(int f=0;f<game.config;f++){
			for(int g=0;g<game.config;g++){
				bw.write(state1[f][g]);
			}if(f+1<game.config){
				
			
			bw.newLine();
			}
		}
		bw.close();
		
	}
	catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("unable to write file");
	}
		
	}


	public static double minimax(char[][] state,Game game,char opponent_play,int depth,int r, int c,String move,char next){
		//System.out.println("i'm in minimax");
		
	if(move.equals("Raid"))	{
		if(r+1<game.config&&state[r+1][c]==opponent_play){
			state[r+1][c]=next;
		}
		if(r-1>=0&&state[r-1][c]==opponent_play){
			state[r-1][c]=next;
		}
		if(c+1<game.config&&state[r][c+1]==opponent_play){
			state[r][c+1]=next;
		}
		if(c-1>=0&&state[r][c-1]==opponent_play){
			state[r][c-1]=next;
		}

	}
	int count=0;
	for(int i=0;i<game.config;i++){
		for(int j=0;j<game.config;j++){
			if(state[i][j]=='.'){
				count=count+1;
			}
		}
	}
	if(count==0){
		int opponent_sum=0;
		int player_sum=0;
		for(int i=0;i<game.config;i++){
			for(int j=0;j<game.config;j++){
				if(state[i][j]==game.youPlay.charAt(0)){
					player_sum=player_sum+Integer.parseInt(game.cell_values[i][j]);
				}
				else{
					if(state[i][j]==game.opponent_play){
					opponent_sum=opponent_sum+Integer.parseInt(game.cell_values[i][j]);
			}}
					
			}
		
	}
		return player_sum-opponent_sum;

	}
		if(depth==game.depth){
		
			int opponent_sum=0;
			int player_sum=0;
			for(int i=0;i<game.config;i++){
				for(int j=0;j<game.config;j++){
					if(state[i][j]==game.youPlay.charAt(0)){
						player_sum=player_sum+Integer.parseInt(game.cell_values[i][j]);
					}
					else{
						if(state[i][j]==game.opponent_play){
						opponent_sum=opponent_sum+Integer.parseInt(game.cell_values[i][j]);
				}}
						
				}
				
				
			}
			/*for(int k=0;k<game.config;k++){
			for(int b=0;b<game.config;b++){
				System.out.print(state[k][b]);
			}
			System.out.println();
		}*/
		//System.out.println(player_sum-opponent_sum);
			return player_sum-opponent_sum;
		}
		
				
		char nextPlay,currentPlay;
		if(opponent_play=='X'){
			opponent_play='O';
			nextPlay='X';
			currentPlay='O';

		}
		else
		{
			opponent_play='X';
			nextPlay='O';
			currentPlay='X';

		}	

	Double v=Double.POSITIVE_INFINITY;

	for(int i=0;i<game.config;i++){
		for(int j=0;j<game.config;j++){
			if(state[i][j]=='.'){
				//char state[][]=new char[game.config][];
				//char[][] state1=state.clone();
				char[][] state1= new char[game.config][game.config];

				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				state1[i][j]=nextPlay;
				
				v=Math.min(v,maxmin(state1,game,opponent_play,depth+1,i,j,"Stake",nextPlay));
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				//state1=state.clone();

				
			}
		}

	}
	for(int i=0;i<game.config;i++){
		for(int j=0;j<game.config;j++){
				//char state[][]=new char[game.config][];
				//char[][] state1=state.clone();
				char[][] state1= new char[game.config][game.config];
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){

						state1[m][n]=state[m][n];
					}
				}
				//state1[i][j]=nextPlay;
				if(i+1<game.config&&state1[i][j]==nextPlay&&state1[i+1][j]=='.'){
				state1[i+1][j]=nextPlay;
				//System.out.println("one");
				
				v=Math.min(v,maxmin(state1,game,opponent_play,depth+1,i+1,j,"Raid",nextPlay));
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				
				

			}
			if(i-1>=0&&state1[i][j]==nextPlay&&state1[i-1][j]=='.'){
				state1[i-1][j]=nextPlay;
				//System.out.println("two");

				v=Math.min(v,maxmin(state1,game,opponent_play,depth+1,i-1,j,"Raid",nextPlay));
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				
				

			}
			if(j+1<game.config&&state1[i][j]==nextPlay&&state1[i][j+1]=='.'){
				state1[i][j+1]=nextPlay;
				//System.out.println("three");

				v=Math.min(v,maxmin(state1,game,opponent_play,depth+1,i,j+1,"Raid",nextPlay));
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				

			}
			if(j-1>=0&&state1[i][j]==nextPlay&&state1[i][j-1]=='.'){
				state1[i][j-1]=nextPlay;
				//System.out.println("four");

				v=Math.min(v,maxmin(state1,game,opponent_play,depth+1,i,j-1,"Raid",nextPlay));
				
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				
			}

				
				/*for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}*/
				
			
		}

	}
	//System.out.print("v is " + v);

	return v;
	}
	//////


	public static double maxmin(char[][] state,Game game,char opponent_play,int depth,int r,int c,String move,char next){
		//System.out.println("in max now:"+state[0][0]+r+c);
	if(move.equals("Raid")){	
		if((r+1)<game.config&&state[r+1][c]==opponent_play){
			state[r+1][c]=next;
			//System.out.println("tru1");
		}
		if(r>0&&state[r-1][c]==opponent_play){
			state[r-1][c]=next;
			//System.out.println("tru2"+r+c);

		}
		if((c+1)<game.config){
				if(state[r][c+1]==opponent_play){
			state[r][c+1]=next;
			//System.out.println("tru3");

		}
		}
		if(c>0&&state[r][c-1]==opponent_play){
			state[r][c-1]=next;
			//System.out.println("tru4");

		}
	}
	int count=0;
	for(int i=0;i<game.config;i++){
		for(int j=0;j<game.config;j++){
			if(state[i][j]=='.'){
				count=count+1;
			}
		}
	}
	if(count==0){
		int opponent_sum=0;
		int player_sum=0;
		for(int i=0;i<game.config;i++){
			for(int j=0;j<game.config;j++){
				if(state[i][j]==game.youPlay.charAt(0)){
					player_sum=player_sum+Integer.parseInt(game.cell_values[i][j]);
				}
				else{
					if(state[i][j]==game.opponent_play){
					opponent_sum=opponent_sum+Integer.parseInt(game.cell_values[i][j]);
			}}
					
			}
		
	}
		return player_sum-opponent_sum;

	}
		if(depth==game.depth){
		
			int opponent_sum=0;
			int player_sum=0;
			for(int i=0;i<game.config;i++){
				for(int j=0;j<game.config;j++){
					if(state[i][j]==game.youPlay.charAt(0)){
						player_sum=player_sum+Integer.parseInt(game.cell_values[i][j]);
					}
					else
					{
						if(state[i][j]==game.opponent_play){
				
						opponent_sum=opponent_sum+Integer.parseInt(game.cell_values[i][j]);
				}}
						
				}
			}
			/*for(int k=0;k<game.config;k++){
				for(int b=0;b<game.config;b++){
					System.out.print(state[k][b]);
				}
				System.out.println();
			}*/
			//System.out.println(player_sum-opponent_sum);
			return player_sum-opponent_sum;
		}
		
				
			
		char nextPlay,currentPlay;
		if(opponent_play=='X'){
			opponent_play='O';
			nextPlay='X';
			currentPlay='O';
			
		}
		else
		{
			opponent_play='X';
			nextPlay='O';
			currentPlay='X';
		}	

	Double v=Double.NEGATIVE_INFINITY;

	for(int i=0;i<game.config;i++){
		for(int j=0;j<game.config;j++){
			if(state[i][j]=='.'){
				//char state[][]=new char[game.config][];
				//char[][] state1=state.clone();
				char[][] state1= new char[game.config][game.config];
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){

						state1[m][n]=state[m][n];
					}
				}
				state1[i][j]=nextPlay;
				
				v=Math.max(v,minimax(state1,game,opponent_play,depth+1,i,j,"Stake",nextPlay));
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				
			}
		}

	}
	for(int i=0;i<game.config;i++){
		for(int j=0;j<game.config;j++){
			
				//char state[][]=new char[game.config][];
				//char[][] state1=state.clone();
				char[][] state1= new char[game.config][game.config];
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){

						state1[m][n]=state[m][n];
					}
				}
				//state1[i][j]=nextPlay;
				if(i+1<game.config&&state1[i][j]==nextPlay&&state1[i+1][j]=='.'){
				state1[i+1][j]=nextPlay;
				v=Math.max(v,minimax(state1,game,opponent_play,depth+1,i+1,j,"Raid",nextPlay));
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}

			}
			if(i-1>=0&&state1[i][j]==nextPlay&&state1[i-1][j]=='.'){
				state1[i-1][j]=nextPlay;
				v=Math.max(v,minimax(state1,game,opponent_play,depth+1,i-1,j,"Raid",nextPlay));
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
			}
			if(j+1<game.config&&state1[i][j]==nextPlay&&state1[i][j+1]=='.'){
				state1[i][j+1]=nextPlay;
				v=Math.max(v,minimax(state1,game,opponent_play,depth+1,i,j+1,"Raid",nextPlay));
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
			}
			if(j-1>=0&&state1[i][j]==nextPlay&&state1[i][j-1]=='.'){
				state1[i][j-1]=nextPlay;
				v=Math.max(v,minimax(state1,game,opponent_play,depth+1,i,j-1,"Raid",nextPlay));
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}	

			}

				
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				
			
		}

	}


	return v;
	}
	public static double minimaxab(char[][] state,Game game,char opponent_play,int depth,int r, int c,String move,char next,Double alpha,Double beta){
		//System.out.println("i'm in minimax");
	if(move.equals("Raid"))	{
		if(r+1<game.config&&state[r+1][c]==opponent_play){
			state[r+1][c]=next;
		}
		if(r-1>=0&&state[r-1][c]==opponent_play){
			state[r-1][c]=next;
		}
		if(c+1<game.config&&state[r][c+1]==opponent_play){
			state[r][c+1]=next;
		}
		if(c-1>=0&&state[r][c-1]==opponent_play){
			state[r][c-1]=next;
		}

	}
	int count=0;
	for(int i=0;i<game.config;i++){
		for(int j=0;j<game.config;j++){
			if(state[i][j]=='.'){
				count=count+1;
			}
		}
	}
	if(count==0){
		int opponent_sum=0;
		int player_sum=0;
		for(int i=0;i<game.config;i++){
			for(int j=0;j<game.config;j++){
				if(state[i][j]==game.youPlay.charAt(0)){
					player_sum=player_sum+Integer.parseInt(game.cell_values[i][j]);
				}
				else{
					if(state[i][j]==game.opponent_play){
					opponent_sum=opponent_sum+Integer.parseInt(game.cell_values[i][j]);
			}}
					
			}
		
	}
		return player_sum-opponent_sum;

	}
		if(depth==game.depth){
		
			int opponent_sum=0;
			int player_sum=0;
			for(int i=0;i<game.config;i++){
				for(int j=0;j<game.config;j++){
					if(state[i][j]==game.youPlay.charAt(0)){
						player_sum=player_sum+Integer.parseInt(game.cell_values[i][j]);
					}
					else{
						if(state[i][j]==game.opponent_play){
						opponent_sum=opponent_sum+Integer.parseInt(game.cell_values[i][j]);
				}}
						
				}
				
				
			}
			
			return player_sum-opponent_sum;
		}
		
				
		char nextPlay,currentPlay;
		if(opponent_play=='X'){
			opponent_play='O';
			nextPlay='X';
			currentPlay='O';

		}
		else
		{
			opponent_play='X';
			nextPlay='O';
			currentPlay='X';

		}	

	Double v=Double.POSITIVE_INFINITY;

	for(int i=0;i<game.config;i++){
		for(int j=0;j<game.config;j++){
			if(state[i][j]=='.'){
				//char state[][]=new char[game.config][];
				//char[][] state1=state.clone();
				char[][] state1= new char[game.config][game.config];

				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				state1[i][j]=nextPlay;
				
				v=Math.min(v,maxminab(state1,game,opponent_play,depth+1,i,j,"Stake",nextPlay,alpha,beta));
				if(v<=alpha){
					return v;
				}
				beta=Math.min(beta, v);
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				//state1=state.clone();

				
			}
		}

	}
	for(int i=0;i<game.config;i++){
		for(int j=0;j<game.config;j++){
				//char state[][]=new char[game.config][];
				//char[][] state1=state.clone();
				char[][] state1= new char[game.config][game.config];
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){

						state1[m][n]=state[m][n];
					}
				}
				//state1[i][j]=nextPlay;
				if(i+1<game.config&&state1[i][j]==nextPlay&&state1[i+1][j]=='.'){
				state1[i+1][j]=nextPlay;
				//System.out.println("one");
				
				v=Math.min(v,maxminab(state1,game,opponent_play,depth+1,i+1,j,"Raid",nextPlay,alpha,beta));
				if(v<=alpha){
					return v;
				}
				beta=Math.min(beta, v);
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				
				

			}
			if(i-1>=0&&state[i][j]==nextPlay&&state1[i-1][j]=='.'){
				state1[i-1][j]=nextPlay;
				//System.out.println("two");

				v=Math.min(v,maxminab(state1,game,opponent_play,depth+1,i-1,j,"Raid",nextPlay,alpha,beta));
				if(v<=alpha){
					return v;
				}
				beta=Math.min(beta, v);
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				
				

			}
			if(j+1<game.config&&state[i][j]==nextPlay&&state1[i][j+1]=='.'){
				state1[i][j+1]=nextPlay;
				//System.out.println("three");

				v=Math.min(v,maxminab(state1,game,opponent_play,depth+1,i,j+1,"Raid",nextPlay,alpha,beta));
				if(v<=alpha){
					return v;
				}
				beta=Math.min(beta, v);
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				

			}
			if(j-1>=0&&state[i][j]==nextPlay&&state1[i][j-1]=='.'){
				state1[i][j-1]=nextPlay;
				//System.out.println("four");

				v=Math.min(v,maxminab(state1,game,opponent_play,depth+1,i,j-1,"Raid",nextPlay,alpha,beta));
				if(v<=alpha){
					return v;
				}
				beta=Math.min(beta, v);
				
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				
			}

				
				/*for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}*/
				
			
		}

	}
	//System.out.print("v is " + v);

	return v;
	}



	public static double maxminab(char[][] state,Game game,char opponent_play,int depth,int r,int c,String move,char next,Double alpha, Double beta){
		//System.out.println("in max now:"+state[0][0]+r+c);
	if(move.equals("Raid")){	
		if((r+1)<game.config&&state[r+1][c]==opponent_play){
			state[r+1][c]=next;
			//System.out.println("tru1");
		}
		if(r>0&&state[r-1][c]==opponent_play){
			state[r-1][c]=next;
			//System.out.println("tru2"+r+c);

		}
		if((c+1)<game.config){
				if(state[r][c+1]==opponent_play){
			state[r][c+1]=next;
			//System.out.println("tru3");

		}
		}
		if(c>0&&state[r][c-1]==opponent_play){
			state[r][c-1]=next;
			//System.out.println("tru4");

		}
	}
	
	int count=0;
	for(int i=0;i<game.config;i++){
		for(int j=0;j<game.config;j++){
			if(state[i][j]=='.'){
				count=count+1;
			}
		}
	}
	if(count==0){
		int opponent_sum=0;
		int player_sum=0;
		for(int i=0;i<game.config;i++){
			for(int j=0;j<game.config;j++){
				if(state[i][j]==game.youPlay.charAt(0)){
					player_sum=player_sum+Integer.parseInt(game.cell_values[i][j]);
				}
				else{
					if(state[i][j]==game.opponent_play){
					opponent_sum=opponent_sum+Integer.parseInt(game.cell_values[i][j]);
			}}
					
			}
		
	}
		return player_sum-opponent_sum;

	}
		if(depth==game.depth){
		
			int opponent_sum=0;
			int player_sum=0;
			for(int i=0;i<game.config;i++){
				for(int j=0;j<game.config;j++){
					if(state[i][j]==game.youPlay.charAt(0)){
						player_sum=player_sum+Integer.parseInt(game.cell_values[i][j]);
					}
					else
					{
						if(state[i][j]==game.opponent_play){
				
						opponent_sum=opponent_sum+Integer.parseInt(game.cell_values[i][j]);
				}}
						
				}
			}
			/*for(int k=0;k<game.config;k++){
				for(int b=0;b<game.config;b++){
					System.out.print(state[k][b]);
				}
				System.out.println();
			}
			System.out.println(player_sum-opponent_sum);*/
			return player_sum-opponent_sum;
		}
		
				
			
		char nextPlay,currentPlay;
		if(opponent_play=='X'){
			opponent_play='O';
			nextPlay='X';
			currentPlay='O';
			
		}
		else
		{
			opponent_play='X';
			nextPlay='O';
			currentPlay='X';
		}	

	Double v=Double.NEGATIVE_INFINITY;

	for(int i=0;i<game.config;i++){
		for(int j=0;j<game.config;j++){
			if(state[i][j]=='.'){
				//char state[][]=new char[game.config][];
				//char[][] state1=state.clone();
				char[][] state1= new char[game.config][game.config];
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){

						state1[m][n]=state[m][n];
					}
				}
				state1[i][j]=nextPlay;
				
				v=Math.max(v,minimaxab(state1,game,opponent_play,depth+1,i,j,"Stake",nextPlay, alpha, beta));
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				if(v>=beta){
					return v;
				}
				alpha=Math.max(v, alpha);
				
			}
		}

	}
	for(int i=0;i<game.config;i++){
		for(int j=0;j<game.config;j++){
			
				//char state[][]=new char[game.config][];
				//char[][] state1=state.clone();
				char[][] state1= new char[game.config][game.config];
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){

						state1[m][n]=state[m][n];
					}
				}
				//state1[i][j]=nextPlay;
				if(i+1<game.config&&state1[i][j]==nextPlay&&state1[i+1][j]=='.'){
				state1[i+1][j]=nextPlay;
				v=Math.max(v,minimaxab(state1,game,opponent_play,depth+1,i+1,j,"Raid",nextPlay, alpha, beta));
				if(v>=beta){
					return v;
				}
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				alpha=Math.max(v, alpha);

			}
			if(i-1>=0&&state1[i][j]==nextPlay&&state1[i-1][j]=='.'){
				state1[i-1][j]=nextPlay;
				v=Math.max(v,minimaxab(state1,game,opponent_play,depth+1,i-1,j,"Raid",nextPlay, alpha, beta));
				if(v>=beta){
					return v;
				}
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				alpha=Math.max(v, alpha);
			}
			if(j+1<game.config&&state1[i][j]==nextPlay&&state1[i][j+1]=='.'){
				state1[i][j+1]=nextPlay;
				v=Math.max(v,minimaxab(state1,game,opponent_play,depth+1,i,j+1,"Raid",nextPlay, alpha,  beta));
				if(v>=beta){
					return v;
				}
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				alpha=Math.max(v, alpha);
			}
			if(j-1>=0&&state1[i][j]==nextPlay&&state1[i][j-1]=='.'){
				state1[i][j-1]=nextPlay;
				v=Math.max(v,minimaxab(state1,game,opponent_play,depth+1,i,j-1,"Raid",nextPlay, alpha, beta));
				if(v>=beta){
					return v;
				}
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				alpha=Math.max(v, alpha);

			}

				
				for(int m=0;m<game.config;m++){
					for(int n=0;n<game.config;n++){
						state1[m][n]=state[m][n];
					}
				}
				
			
		}

	}


	return v;
	}
	}

 class Game {

	int config;
	String mode;
	String youPlay;
	int depth;
	String[][] cell_values= new String[config][config];
	char[][] board_state=new char[config][config];
	String position;
	String moveType;
	char opponent_play;
	Double result=Double.NEGATIVE_INFINITY;
	public int getConfig() {
		return config;
	}
	public void setConfig(int config) {
		this.config = config;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getYouPlay() {
		return youPlay;
	}
	public void setYouPlay(String youPlay) {
		this.youPlay = youPlay;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String[][] getCell_values() {
		return cell_values;
	}
	public void setCell_values(String[][] cell_values) {
		this.cell_values = cell_values;
	}
	public char[][] getBoard_state() {
		return board_state;
	}
	public void setBoard_state(char[][] board_state) {
		this.board_state = board_state;
	}
	
}

	

