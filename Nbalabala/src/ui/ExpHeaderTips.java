package ui;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.JTableHeader;
  
  
public class ExpHeaderTips {   
       
    private JTable table;   
       
    String[] namestooltip={"wow ncp  name","some word"};   
       
    public ExpHeaderTips(){   
        JFrame f = new JFrame();   
        f.setSize(300, 200);   
        Object[][] playerInfo = {   
                { "提里奥.弗丁", "种族不代表荣耀，我见过最高尚的兽人，也见过最卑劣的人类。" },   
                { "莫格莱尼", "生命本身毫无意义，只有死亡才能让你了解人性的真谛。"} };   
        String[] Names = { "姓名", "台词" };   
           
           
        table = new JTable(playerInfo, Names){   
            public String getToolTipText(MouseEvent e) {   
                int row=table.rowAtPoint(e.getPoint());   
                int col=table.columnAtPoint(e.getPoint());   
                String tiptextString=null;   
                if(row>-1 && col>-1){   
                    Object value=table.getValueAt(row, col);   
                    if(null!=value && !"".equals(value))   
                        tiptextString=value.toString();//悬浮显示单元格内容  
                }   
                return tiptextString;   
            }   
               
             protected JTableHeader createDefaultTableHeader() {   
                    return new JTableHeader(columnModel) {   
                        public String getToolTipText(MouseEvent e) {   
                            String tip = null;   
                            java.awt.Point p = e.getPoint();   
                            int index = columnModel.getColumnIndexAtX(p.x);   
                            int realIndex =  columnModel.getColumn(index).getModelIndex();   
                            return namestooltip[realIndex];   
                        }   
                    };   
                }   
  
        };   
           
        //悬浮提示单元格的值    
//      table.addMouseMotionListener(new MouseAdapter(){  
//          public void mouseMoved(MouseEvent e) {  
//              int row=table.rowAtPoint(e.getPoint());  
//              int col=table.columnAtPoint(e.getPoint());  
//              if(row>-1 && col>-1){   
//                  Object value=table.getValueAt(row, col);  
//                  if(null!=value && !"".equals(value))  
//                      table.setToolTipText(value.toString());//悬浮显示单元格内容  
//                  else   
//                      table.setToolTipText(null);//关闭提示  
//              }   
//          }   
//      });   
           
        JScrollPane scrollPane = new JScrollPane(table);   
        f.getContentPane().add(scrollPane, BorderLayout.CENTER);   
        f.setTitle("单元格悬浮提示");   
        f.setLocationRelativeTo(null);   
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   
        f.setVisible(true);   
           
    }   
       
    public static void main(String[] args) {
    	DecimalFormat df = new DecimalFormat("##.0%");
    	System.out.println(df.format(0.55));
//        new ExpHeaderTips();   
    }   
  
}  
