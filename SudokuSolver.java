import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuSolver{
    public static void main(String[] args) {
        System.out.println("Hello World");
        Integer[] b = {4,0,0,0,6,0,0,5,2,
        0,5,0,0,4,0,7,0,0,
        6,3,2,0,0,0,1,8,0,
        0,0,0,0,0,5,8,1,0,
        2,0,0,0,0,0,0,6,7,
        0,0,6,0,1,0,0,3,5,
        5,7,0,4,3,0,6,2,0,
        1,6,0,0,7,0,9,0,0,
        0,0,0,1,0,0,0,0,0};
        SudokuSolver s = new SudokuSolver(b);
        //System.out.println(Arrays.toString(s.board));
        //System.out.println(s.solve());
        System.out.println(s.printBoard(s.solve()));
    }
    private Integer[] board = new Integer[81];

    public SudokuSolver(Integer[] board){
      this.board = board;
    }

    public List<Integer> solve(){
      List<Integer> solution = Arrays.asList(this.getBoard());
      //Arrays.fill(this.getBoard(),1);
      List<Integer> board = Arrays.asList(this.getBoard());
      while(solution.contains(0)){
        
        //System.out.println(board);
        for(int i=0;i<9;i++){
          for(int j=0;j<9;j++){
            ArrayList<Integer> vals = new ArrayList<Integer>();
            for(int k=1;k<10;k++){
              ArrayList<Integer> rows = new ArrayList<Integer>();
              for(int l=0;l<9;l++){
                rows.add(board.get(9*i+l));
              } 
              ArrayList<Integer> cols = new ArrayList<Integer>();
              for(int l=0;l<9;l++){
                cols.add(board.get(9*l+j));
              }
              ArrayList<Integer> box = new ArrayList<Integer>();
              if(i<3&&j<3){
                for(int l=0;l<3;l++){
                  for(int m=0;m<3;m++){
                    box.add(board.get(9*l+m));
                  }
                }
              }
              else if(i<6&&i>=3&&j<3){
                for(int l=3;l<6;l++){
                  for(int m=0;m<3;m++){
                    box.add(board.get(9*l+m));
                  }
                }
              }
              else if(i<9&&i>=6&&j<3){
                for(int l=6;l<9;l++){
                  for(int m=0;m<3;m++){
                    box.add(board.get(9*l+m));
                  }
                }
              }
              else if(i<3&&i>=0&&j<6&&j>=3){
                for(int l=0;l<3;l++){
                  for(int m=3;m<6;m++){
                    box.add(board.get(9*l+m));
                  }
                }
              }
              else if(i<6&&i>=3&&j<6&&j>=3){
                for(int l=3;l<6;l++){
                  for(int m=3;m<6;m++){
                    box.add(board.get(9*l+m));
                  }
                }
              }
              else if(i<9&&i>=6&&j<6&&j>=3){
                for(int l=6;l<9;l++){
                  for(int m=3;m<6;m++){
                    box.add(board.get(9*l+m));
                  }
                }
              }
              else if(i<3&&i>=0&&j<9&&j>=6){
                for(int l=0;l<3;l++){
                  for(int m=6;m<9;m++){
                    box.add(board.get(9*l+m));
                  }
                }
              }
              else if(i<6&&i>=3&&j<9&&j>=6){
                for(int l=3;l<6;l++){
                  for(int m=6;m<9;m++){
                    box.add(board.get(9*l+m));
                  }
                }
              }
              else if(i<9&&i>=6&&j<9&&j>=6){
                for(int l=6;l<9;l++){
                  for(int m=6;m<9;m++){
                    box.add(board.get(9*l+m));
                  }
                }
              }
              //System.out.println(9*i+","+j+","+k+","+rows+","+cols+","+box);
              if(!rows.contains(k) && !cols.contains(k) &&!box.contains(k)&&board.get(9*i+j)==0){
                //System.out.println("value:"+k+","+"index:"+i+","+j);
                //System.out.println(k);
                vals.add(k);
              }
            }
            //System.out.println(vals+"index:"+i+","+j);
            if(vals.size()==1){
              if(board.get(9*i+j)==0){
                solution.set(9*i+j,vals.get(0));
                //System.out.println(9*i+j+",!"+vals.get(0));
                //System.out.println(board.equals(solution));
                //System.out.println(solution);
              }
            }
          }
        }
      }
      
      return solution;
    }


    public Integer[] getBoard(){
      return this.board;
    }

    public String printBoard(List<Integer> board){
      String output = "";
      for(int i=0;i<9;i++){
        for(int j=0;j<9;j++){
          output+=board.get(9*i+j);
        }
        output+="\n";
      }

      return output;
    }

}