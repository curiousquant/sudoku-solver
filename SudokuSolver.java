import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.time.LocalTime;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class SudokuSolver{
  public static final int MAX_SIZE_BOARD=81;
  public static final int ROW_SIZE=9;
  public static final int COL_SIZE=9;
    public static void main(String[] args) {
        System.out.println("Hello World");
        //testpush
        SudokuSolver s = new SudokuSolver();
        s.setBoard(s.readBoard());
        //System.out.println(s.readBoard().toString());
        //System.out.println(Arrays.toString(s.board));
        System.out.println(s.printBoard(s.solve()));
    }

    private ArrayList<Integer> board = new ArrayList<Integer>();

    public void setBoard(ArrayList<Integer> board){
      this.board = board;
    }

    public ArrayList<Integer> readBoard(){
      ArrayList<Integer> list = new ArrayList<Integer>();
      try{
        File myObj1 = new File("board.txt");
        Scanner myReader = new Scanner(myObj1);
        System.out.println("write has completed");

        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          List<String> temp = Arrays.asList(data.split(","));
          for(int i=0;i<temp.size();i++){
            list.add(Integer.parseInt(temp.get(i)));
          }
        }
        myReader.close();
        return list;
      }
      catch(Exception e){
        System.out.println(e);
      }
      return list;
    }
    
    public List<Integer> solve3(){
      LocalTime myObj = LocalTime.now();
      Random r = new Random();
      List<Integer> initboard = new ArrayList<>();
      //Arrays.fill(this.getBoard(),1);
      
      List<Integer> b = this.getBoard(); 
      for(int i=0;i<this.getBoard().size();i++){
        initboard.add(b.get(i));
      }
        //System.out.println(board);
        ArrayList<ArrayList<Integer>> possiblities = new ArrayList<ArrayList<Integer>>();
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
              if(!this.checkBoard(b) && !rows.contains(k) && !cols.contains(k) &&!box.contains(k)&&b.get(9*i+j)==0){
                //System.out.println("value:"+k+","+"index:"+i+","+j);
                //System.out.println(k);
                vals.add(k);
              }
              if(b.get(9*i+j)!=0 && vals.size()==0){
                vals.add(b.get(9*i+j));
              }
            }
            //System.out.println(vals+"index:"+i+","+j);
            //if(vals.size()==1){
              
              possiblities.add(vals);
              
              
              int rand = -1;
              if(vals.size()>0){
                rand = r.nextInt(vals.size());
              }
              

              if(b.get(9*i+j)==0){
                if(rand!=-1){
                  //b.set(9*i+j,vals.get(rand));
                  //vals.remove(rand);
                }
                //System.out.println(9*i+j+",!"+vals.get(0));
                //System.out.println(board.equals(solution));
                //System.out.println(solution);
                
              }
            }
          //}
        }
        
        //System.out.println(possiblities.get(1).size());
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        try{
        FileWriter myWriter = new FileWriter("oceans.txt");
        for(int i1=0;i1<possiblities.get(0).size();i1++){
          for(int i2=0;i2<possiblities.get(1).size();i2++){
            for(int i3=0;i3<possiblities.get(2).size();i3++){
              for(int i4=0;i4<possiblities.get(3).size();i4++){
                for(int i5=0;i5<possiblities.get(4).size();i5++){
                  for(int i6=0;i6<possiblities.get(5).size();i6++){
                    for(int i7=0;i7<possiblities.get(6).size();i7++){
            for(int i8=0;i8<possiblities.get(7).size();i8++){
          for(int i9=0;i9<possiblities.get(8).size();i9++){
            for(int i10=0;i10<possiblities.get(9).size();i10++){
              for(int i11=0;i11<possiblities.get(10).size();i11++){
                for(int i12=0;i12<possiblities.get(11).size();i12++){
                  for(int i13=0;i13<possiblities.get(12).size();i13++){
                    for(int i14=0;i14<possiblities.get(13).size();i14++){
                      for(int i15=0;i15<possiblities.get(14).size();i15++){
                        for(int i16=0;i16<possiblities.get(15).size();i16++){
                          for(int i17=0;i17<possiblities.get(16).size();i17++){
                            for(int i18=0;i18<possiblities.get(17).size();i18++){
                              for(int i19=0;i19<possiblities.get(18).size();i19++){
                                for(int i20=0;i20<possiblities.get(19).size();i20++){
                                  for(int i21=0;i21<possiblities.get(20).size();i21++){
                                    for(int i22=0;i22<possiblities.get(21).size();i22++){
                                      for(int i23=0;i23<possiblities.get(22).size();i23++){
                                        for(int i24=0;i24<possiblities.get(23).size();i24++){
                                for(int i25=0;i25<possiblities.get(24).size();i25++){
                              for(int i26=0;i26<possiblities.get(25).size();i26++){
                                for(int i27=0;i27<possiblities.get(26).size();i27++){
                                  for(int i28=0;i28<possiblities.get(27).size();i28++){
                                    for(int i29=0;i29<possiblities.get(28).size();i29++){
                                      for(int i30=0;i30<possiblities.get(29).size();i30++){
                                        for(int i31=0;i31<possiblities.get(30).size();i31++){
                                          for(int i32=0;i32<possiblities.get(31).size();i32++){
                                            for(int i33=0;i33<possiblities.get(32).size();i33++){
                                              for(int i34=0;i34<possiblities.get(33).size();i34++){
                                                for(int i35=0;i35<possiblities.get(34).size();i35++){
                                                  for(int i36=0;i36<possiblities.get(35).size();i36++){
                                                    for(int i37=0;i37<possiblities.get(36).size();i37++){
                                                      for(int i38=0;i38<possiblities.get(37).size();i38++){
                                                        for(int i39=0;i39<possiblities.get(38).size();i39++){
                                                          for(int i40=0;i40<possiblities.get(39).size();i40++){
                                                            for(int i41=0;i41<possiblities.get(40).size();i41++){
                                                    for(int i42=0;i42<possiblities.get(41).size();i42++){
                                                  for(int i43=0;i43<possiblities.get(42).size();i43++){
                                                    for(int i44=0;i44<possiblities.get(43).size();i44++){
                                                      for(int i45=0;i45<possiblities.get(44).size();i45++){
                                                        for(int i46=0;i46<possiblities.get(45).size();i46++){
                                                          for(int i47=0;i47<possiblities.get(46).size();i47++){
                                                            for(int i48=0;i48<possiblities.get(47).size();i48++){
                                                              for(int i49=0;i49<possiblities.get(48).size();i49++){
                                                                for(int i50=0;i50<possiblities.get(49).size();i50++){
                                                                  for(int i51=0;i51<possiblities.get(50).size();i51++){
                                                                    for(int i52=0;i52<possiblities.get(51).size();i52++){
                                                                      for(int i53=0;i53<possiblities.get(52).size();i53++){
                                                                        for(int i54=0;i54<possiblities.get(53).size();i54++){
                                                                          for(int i55=0;i55<possiblities.get(54).size();i55++){
                                                                            for(int i56=0;i56<possiblities.get(55).size();i56++){
                                                                              for(int i57=0;i57<possiblities.get(56).size();i57++){
                                                                                for(int i58=0;i58<possiblities.get(57).size();i58++){
                                                                        for(int i59=0;i59<possiblities.get(58).size();i59++){
                                                                      for(int i60=0;i60<possiblities.get(59).size();i60++){
                                                                        for(int i61=0;i61<possiblities.get(60).size();i61++){
                                                                          for(int i62=0;i62<possiblities.get(61).size();i62++){
                                                                            for(int i63=0;i63<possiblities.get(62).size();i63++){
                                                                              for(int i64=0;i64<possiblities.get(63).size();i64++){
                                                                                for(int i65=0;i65<possiblities.get(64).size();i65++){
                                                                                  for(int i66=0;i66<possiblities.get(65).size();i66++){
                                                                                    for(int i67=0;i67<possiblities.get(66).size();i67++){
                                                                                      for(int i68=0;i68<possiblities.get(67).size();i68++){
                                                                                        for(int i69=0;i69<possiblities.get(68).size();i69++){
                                                                                          for(int i70=0;i70<possiblities.get(69).size();i70++){
                                                                                            for(int i71=0;i71<possiblities.get(70).size();i71++){
                                                                                              for(int i72=0;i72<possiblities.get(71).size();i72++){
                                                                                                for(int i73=0;i73<possiblities.get(72).size();i73++){
                                                                                                  for(int i74=0;i74<possiblities.get(73).size();i74++){
                                                                                                    for(int i75=0;i75<possiblities.get(74).size();i75++){
                                                                                            for(int i76=0;i76<possiblities.get(75).size();i76++){
                                                                                          for(int i77=0;i77<possiblities.get(76).size();i77++){
                                                                                            for(int i78=0;i78<possiblities.get(77).size();i78++){
                                                                                              for(int i79=0;i79<possiblities.get(78).size();i79++){
                                                                                                for(int i80=0;i80<possiblities.get(79).size();i80++){
                                                                                                  for(int i81=0;i81<possiblities.get(80).size();i81++){
                                                                                                      ArrayList<Integer> tempresult = new ArrayList<Integer>();
                                                                                                      tempresult.add(possiblities.get(0).get(i1));
                                                                                                      tempresult.add(possiblities.get(1).get(i2));
                                                                                                      tempresult.add(possiblities.get(2).get(i3));
                                                                                                      tempresult.add(possiblities.get(3).get(i4));
                                                                                                      tempresult.add(possiblities.get(4).get(i5));
                                                                                                      tempresult.add(possiblities.get(5).get(i6));
                                                                                                      tempresult.add(possiblities.get(6).get(i7));
                                                                                                      tempresult.add(possiblities.get(7).get(i8));
                                                                                                      tempresult.add(possiblities.get(8).get(i9));

                                                                                                      tempresult.add(possiblities.get(9).get(i10));
                                                                                                      tempresult.add(possiblities.get(10).get(i11));
                                                                                                      tempresult.add(possiblities.get(11).get(i12));
                                                                                                      tempresult.add(possiblities.get(12).get(i13));
                                                                                                      tempresult.add(possiblities.get(13).get(i14));
                                                                                                      tempresult.add(possiblities.get(14).get(i15));
                                                                                                      tempresult.add(possiblities.get(15).get(i16));
                                                                                                      tempresult.add(possiblities.get(16).get(i17));
                                                                                                      tempresult.add(possiblities.get(17).get(i18));

                                                                                                      tempresult.add(possiblities.get(18).get(i19));
                                                                                                      tempresult.add(possiblities.get(19).get(i20));
                                                                                                      tempresult.add(possiblities.get(20).get(i21));
                                                                                                      tempresult.add(possiblities.get(21).get(i22));
                                                                                                      tempresult.add(possiblities.get(22).get(i23));
                                                                                                      tempresult.add(possiblities.get(23).get(i24));
                                                                                                      tempresult.add(possiblities.get(24).get(i25));
                                                                                                      tempresult.add(possiblities.get(25).get(i26));
                                                                                                      tempresult.add(possiblities.get(26).get(i27));

                                                                                                      tempresult.add(possiblities.get(27).get(i28));
                                                                                                      tempresult.add(possiblities.get(28).get(i29));
                                                                                                      tempresult.add(possiblities.get(29).get(i30));
                                                                                                      tempresult.add(possiblities.get(30).get(i31));
                                                                                                      tempresult.add(possiblities.get(31).get(i32));
                                                                                                      tempresult.add(possiblities.get(32).get(i33));
                                                                                                      tempresult.add(possiblities.get(33).get(i34));
                                                                                                      tempresult.add(possiblities.get(34).get(i35));
                                                                                                      tempresult.add(possiblities.get(35).get(i36));

                                                                                                      tempresult.add(possiblities.get(36).get(i37));
                                                                                                      tempresult.add(possiblities.get(37).get(i38));
                                                                                                      tempresult.add(possiblities.get(38).get(i39));
                                                                                                      tempresult.add(possiblities.get(39).get(i40));
                                                                                                      tempresult.add(possiblities.get(40).get(i41));
                                                                                                      tempresult.add(possiblities.get(41).get(i42));
                                                                                                      tempresult.add(possiblities.get(42).get(i43));
                                                                                                      tempresult.add(possiblities.get(43).get(i44));
                                                                                                      tempresult.add(possiblities.get(44).get(i45));

                                                                                                      tempresult.add(possiblities.get(45).get(i46));
                                                                                                      tempresult.add(possiblities.get(46).get(i47));
                                                                                                      tempresult.add(possiblities.get(47).get(i48));
                                                                                                      tempresult.add(possiblities.get(48).get(i49));
                                                                                                      tempresult.add(possiblities.get(49).get(i50));
                                                                                                      tempresult.add(possiblities.get(50).get(i51));
                                                                                                      tempresult.add(possiblities.get(51).get(i52));
                                                                                                      tempresult.add(possiblities.get(52).get(i53));
                                                                                                      tempresult.add(possiblities.get(53).get(i54));

                                                                                                      tempresult.add(possiblities.get(54).get(i55));
                                                                                                      tempresult.add(possiblities.get(55).get(i56));
                                                                                                      tempresult.add(possiblities.get(56).get(i57));
                                                                                                      tempresult.add(possiblities.get(57).get(i58));
                                                                                                      tempresult.add(possiblities.get(58).get(i59));
                                                                                                      tempresult.add(possiblities.get(59).get(i60));
                                                                                                      tempresult.add(possiblities.get(60).get(i61));
                                                                                                      tempresult.add(possiblities.get(61).get(i62));
                                                                                                      tempresult.add(possiblities.get(62).get(i63));

                                                                                                      tempresult.add(possiblities.get(63).get(i64));
                                                                                                      tempresult.add(possiblities.get(64).get(i65));
                                                                                                      tempresult.add(possiblities.get(65).get(i66));
                                                                                                      tempresult.add(possiblities.get(66).get(i67));
                                                                                                      tempresult.add(possiblities.get(67).get(i68));
                                                                                                      tempresult.add(possiblities.get(68).get(i69));
                                                                                                      tempresult.add(possiblities.get(69).get(i70));
                                                                                                      tempresult.add(possiblities.get(70).get(i71));
                                                                                                      tempresult.add(possiblities.get(71).get(i72));

                                                                                                      tempresult.add(possiblities.get(72).get(i73));
                                                                                                      tempresult.add(possiblities.get(73).get(i74));
                                                                                                      tempresult.add(possiblities.get(74).get(i75));
                                                                                                      tempresult.add(possiblities.get(75).get(i76));
                                                                                                      tempresult.add(possiblities.get(76).get(i77));
                                                                                                      tempresult.add(possiblities.get(77).get(i78));
                                                                                                      tempresult.add(possiblities.get(78).get(i79));
                                                                                                      tempresult.add(possiblities.get(79).get(i80));
                                                                                                      tempresult.add(possiblities.get(80).get(i81));
                                                                                                      
                                                                                                      myWriter.write(tempresult.toString()+"\n");
                                                                                                      
                                                                                                    
                                                                                                  }
                                                                                                }
                                                                                              }
                                                                                            }
                                                                                          }
                                                                                        }
                                                                                                    }
                                                                                                  }
                                                                                                }
                                                                                              }
                                                                                            }
                                                                                          }
                                                                                        }
                                                                                      }
                                                                                    }
                                                                                  }
                                                                                }
                                                                              }
                                                                            }
                                                                          }
                                                                        }
                                                                      }
                                                                    }
                                                                                }
                                                                              }
                                                                            }
                                                                          }
                                                                        }
                                                                      }
                                                                    }
                                                                  }
                                                                }
                                                              }
                                                            }
                                                          }
                                                        }
                                                      }
                                                    }
                                                  }
                                                }
                                                            }
                                                          }
                                                        }
                                                      }
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
                    }
                  }
                }
              }
            }
          }
        }
        myWriter.close();
      }
      catch (IOException e){
        System.out.println("An error occurred.");
        e.printStackTrace();
      }

      try {
        File myObj1 = new File("oceans.txt");
        Scanner myReader = new Scanner(myObj1);
        System.out.println("write has completed");
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          ArrayList<Integer> t = new ArrayList<Integer>();
          List<String> list = Arrays.asList(data.substring(1, data.length() - 1).split(", "));
          
          for(int i=0;i<ROW_SIZE;i++){
            for(int j=0;j<COL_SIZE;j++){
              if(!checkBoard(b)){
                String values = list.get(9*i+j);
                b.set(9*i+j, Integer.parseInt(values));
              }
              else{
                myReader.close();
                return b;
              }
              
            }
          }
        }
        myReader.close();
        if (myObj1.delete()) { 
          System.out.println("Deleted the file: " + myObj1.getName());
        } else {
          System.out.println("Failed to delete the file.");
        } 
      } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
      

        // System.out.println(result.size());
        // for(int i=0;i<result.size();i++){
          
        //   System.out.println(result.get(i));
        // }
        float maxsize =1;
        for(int i=0;i<possiblities.size();i++){
          maxsize=maxsize*possiblities.get(i).size();
          //System.out.println(maxsize);
          //System.out.println("-----");
        }
        System.out.println(maxsize+"!");


        //ArrayList<String> result = new ArrayList<>();
        //generatePermutations(possiblities, result, 0, "");
        // for(int i=0;i<result.size();i++){
        //   int count =0;
        //   for(int j=0;j<ROW_SIZE;j++){
        //     for(int k=0;k<COL_SIZE;k++){
        //       if(checkBoard(b)){
        //         return b;
        //       }
        //       else if(b.get(9*j+k)==0){
        //         b.set(9*j+k,(int)result.get(i).charAt(count));
        //         count++;
        //       }
              
        //     }
        //   }
        // }
        //System.out.println(result);
        //System.out.println("!"+b);
      
      return b;
    }

    public List<Integer> solve(){
      LocalTime myObj = LocalTime.now();
      Random r = new Random();
      List<Integer> initboard = new ArrayList<>();
      //Arrays.fill(this.getBoard(),1);
      
      List<Integer> b = this.getBoard();
      for(int i=0;i<this.getBoard().size();i++){
        initboard.add(b.get(i));
      }
      while(!this.checkBoard(b)){
        
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
              if(!this.checkBoard(b) && !rows.contains(k) && !cols.contains(k) &&!box.contains(k)&&b.get(9*i+j)==0){
                //System.out.println("value:"+k+","+"index:"+i+","+j);
                //System.out.println(k);
                vals.add(k);
              }
            }
            //System.out.println(vals+"index:"+i+","+j);
            //if(vals.size()==1){
              
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
        //System.out.println("!"+b);
        if(!this.checkBoard(b)){
          //System.out.println("----------------");
          //System.out.println(this.printBoard(b));
          int bsize = b.size();
          b.clear();
          for(int n=0;n<bsize;n++){
            b.add(initboard.get(n));
          }
        }
      }
      LocalTime myObj2 = LocalTime.now();

      System.out.println(myObj2+"|||"+myObj);
      return b;
    }

    public boolean checkBoard(List<Integer> board){
      boolean containZero = !board.contains(0);
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

          for(int k=1;k<10;k++){
            int count = 0;
            for(int l=0;l<box.size();l++){
              if(k==box.get(l)&&box.get(l)!=0){
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
      
      return containZero;
    }

    public List<Integer> solve2(){
      List<Integer> solution = this.getBoard();
      //Arrays.fill(this.getBoard(),1);
      List<Integer> board = this.getBoard();
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


    public ArrayList<Integer> getBoard(){
      return this.board;
    }

    static int factorial(int n) 
    { 
        int res = 1, i; 
        for (i = 2; i <= n; i++) 
            res *= i; 
        return res; 
    }

    public void generatePermutations(ArrayList<ArrayList<Integer>> lists, ArrayList<String> result, int depth, String current) {
      if (depth == lists.size()) {
          result.add(current);
          return;
      }
  
      for (int i = 0; i < lists.get(depth).size(); i++) {
          generatePermutations(lists, result, depth + 1, current + lists.get(depth).get(i));
      }
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