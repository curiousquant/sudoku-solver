import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SudokuSolver{
  final int MAX_SIZE_BOARD=81;
  final int ROW_SIZE=9;
  final int COL_SIZE=9;
    public static void main(String[] args) {
        System.out.println("Hello World");
        Integer[] ex1_bAlmostSolved = {4,9,7,8,6,1,3,5,2,
        8,5,1,2,4,3,7,9,6,
        6,3,2,9,5,7,1,8,4,
        7,4,3,6,2,5,8,1,9,
        2,1,5,3,8,9,4,6,7,
        9,8,6,7,1,4,2,3,5,
        5,7,9,4,3,8,6,2,0,
        1,6,8,5,7,2,9,4,3,
        3,2,4,1,9,0,5,7,0};
        Integer[] ex1_bOriginal = {4,0,0,0,6,0,0,5,2,
          0,5,0,0,4,0,7,0,0,
          6,3,2,0,0,0,1,8,0,
          0,0,0,0,0,5,8,1,0,
          2,0,0,0,0,0,0,6,7,
          0,0,6,0,1,0,0,3,5,
          5,7,0,4,3,0,6,2,0,
          1,6,0,0,7,0,9,0,0,
          0,0,0,1,0,0,0,0,0};
          //hard difficulty
          Integer[] b = {9,7,0,0,1,0,0,0,0,
            0,1,0,0,0,0,9,0,2,
            0,0,5,0,2,0,8,0,0,
            6,0,0,0,8,0,0,9,0,
            0,0,1,5,0,0,7,0,0,
            0,3,0,0,0,0,0,0,5,
            0,0,0,0,3,0,0,0,8,
            0,9,0,0,5,0,4,0,7,
            4,2,0,0,0,1,0,0,0};
        SudokuSolver s = new SudokuSolver(b);
        //System.out.println(Arrays.toString(s.board));
        //System.out.println(s.solve());
        System.out.println(s.printBoard(s.solve()));
    }
    private Integer[] board = new Integer[MAX_SIZE_BOARD];

    public SudokuSolver(Integer[] board){
      this.board = board;
    }

    public List<Integer> solve(){
      List<Integer> initboard = new ArrayList<>();
      //Arrays.fill(this.getBoard(),1);
      
      List<Integer> b = new ArrayList<>(Arrays.asList(this.getBoard()));
      for(int i=0;i<this.getBoard().length;i++){
        initboard.add(b.get(i));
      }
      while(b.contains(0)){
        
        //System.out.println(board);
        for(int i=0;i<ROW_SIZE;i++){
          for(int j=0;j<COL_SIZE;j++){
            ArrayList<Integer> vals = new ArrayList<Integer>();
            for(int k=1;k<10;k++){
              ArrayList<Integer> rows = new ArrayList<Integer>();
              for(int l=0;l<9;l++){
                rows.add(b.get(9*i+l));
              } 
              ArrayList<Integer> cols = new ArrayList<Integer>();
              for(int l=0;l<9;l++){
                cols.add(b.get(9*l+j));
              }
              ArrayList<Integer> box = new ArrayList<Integer>();
              if(i<3&&j<3){
                for(int l=0;l<3;l++){
                  for(int m=0;m<3;m++){
                    box.add(b.get(9*l+m));
                  }
                }
              }
              else if(i<6&&i>=3&&j<3){
                for(int l=3;l<6;l++){
                  for(int m=0;m<3;m++){
                    box.add(b.get(9*l+m));
                  }
                }
              }
              else if(i<9&&i>=6&&j<3){
                for(int l=6;l<9;l++){
                  for(int m=0;m<3;m++){
                    box.add(b.get(9*l+m));
                  }
                }
              }
              else if(i<3&&i>=0&&j<6&&j>=3){
                for(int l=0;l<3;l++){
                  for(int m=3;m<6;m++){
                    box.add(b.get(9*l+m));
                  }
                }
              }
              else if(i<6&&i>=3&&j<6&&j>=3){
                for(int l=3;l<6;l++){
                  for(int m=3;m<6;m++){
                    box.add(b.get(9*l+m));
                  }
                }
              }
              else if(i<9&&i>=6&&j<6&&j>=3){
                for(int l=6;l<9;l++){
                  for(int m=3;m<6;m++){
                    box.add(b.get(9*l+m));
                  }
                }
              }
              else if(i<3&&i>=0&&j<9&&j>=6){
                for(int l=0;l<3;l++){
                  for(int m=6;m<9;m++){
                    box.add(b.get(9*l+m));
                  }
                }
              }
              else if(i<6&&i>=3&&j<9&&j>=6){
                for(int l=3;l<6;l++){
                  for(int m=6;m<9;m++){
                    box.add(b.get(9*l+m));
                  }
                }
              }
              else if(i<9&&i>=6&&j<9&&j>=6){
                for(int l=6;l<9;l++){
                  for(int m=6;m<9;m++){
                    box.add(b.get(9*l+m));
                  }
                }
              }
              //System.out.println(9*i+","+j+","+k+","+rows+","+cols+","+box);
              if(!rows.contains(k) && !cols.contains(k) &&!box.contains(k)&&b.get(9*i+j)==0){
                //System.out.println("value:"+k+","+"index:"+i+","+j);
                //System.out.println(k);
                vals.add(k);
              }
            }
            //System.out.println(vals+"index:"+i+","+j);
            //if(vals.size()==1){
              Random r = new Random();
              int rand = -1;
              if(vals.size()>0){
                rand = r.nextInt(vals.size());
              }
              

              if(b.get(9*i+j)==0){
                if(rand!=-1){
                  b.set(9*i+j,vals.get(rand));
                  //vals.remove(rand);
                }
                //System.out.println(9*i+j+",!"+vals.get(0));
                //System.out.println(board.equals(solution));
                //System.out.println(solution);
                
              }
            }
          //}
        }

        if(!this.checkBoard(b)){
          //System.out.println(b);
          int bsize = b.size();
          b.clear();
          for(int n=0;n<bsize;n++){
            b.add(initboard.get(n));
          }
        }
      }
      
      return b;
    }

    public boolean checkBoard(List<Integer> board){

      for(int i=0;i<ROW_SIZE;i++){
        for(int j=0;j<COL_SIZE;j++){
          ArrayList<Integer> rows = new ArrayList<Integer>();
          for(int l=0;l<9;l++){
            rows.add(board.get(9*i+l));
          } 
          
          ArrayList<Integer> cols = new ArrayList<Integer>();
          for(int l=0;l<9;l++){
            cols.add(board.get(9*l+j));
          }
          
          for(int k=1;k<10;k++){
            int count = 0;
            for(int l=0;l<rows.size();l++){
              if(k==rows.get(l)&&rows.get(l)!=0){
                count++;
              }
              //System.out.println(count+"!"+k+"!"+rows.get(l)+"!"+rows+"!"+cols);
            }
            
            if(count>1){
              return false;
            }
          }
          for(int k=1;k<10;k++){
            int count = 0;
            for(int l=0;l<cols.size();l++){
              if(k==cols.get(l)&&cols.get(l)!=0){
                count++;
              }
              //System.out.println(count+"!"+k+"!"+cols.get(l)+"!"+rows+"!"+cols);
            }
            if(count>1){
              return false;
            }
          }
        }
      }
      
      return true;
    }
    public Integer[] getBoard(){
      return this.board;
    }

    public String printBoard(List<Integer> board){
      String output = "";
      for(int i=0;i<ROW_SIZE;i++){
        for(int j=0;j<COL_SIZE;j++){
          output+=board.get(9*i+j);
        }
        output+="\n";
      }

      return output;
    }

}