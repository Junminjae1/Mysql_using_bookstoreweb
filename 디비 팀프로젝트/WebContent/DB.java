package jogiyo;

  
import java.sql.*;
import java.io.IOException;
import java.util.Vector;

public class DB {

	static  Connection con         = null;
    static  Statement stmt         = null;
    static  ResultSet rs           = null ;
    
    static String driver= "com.mysql.jdbc.Driver";
    static String URL = "jdbc:mysql://localhost:3306/yesdbp" ;;

    public static void loadConnect()  {
 	   		try {
		   		// ������ �ݴ´�.
 		   		if( stmt != null ) stmt.close();
 		   		if( con != null ) con.close();
	   		} catch (SQLException ex ) {};  
	   	             
    		    // ����̹� �ε�
    		 try {
     	         Class.forName(driver);
    	 
    	 	} catch ( java.lang.ClassNotFoundException e ) {
    	         System.err.println("** Driver loaderror in loadConnect: " + e.getMessage() );
    	         e.printStackTrace();
    	         
    		}
    		
    	 	try {
    	         
    	         // �����ϱ�
    	         con  = DriverManager.getConnection(URL, "root", "onlyroot");
    	         
    	         System.out.println("\n"+URL+"�� �����");
  					
    					 
    		} catch( SQLException ex ) 
    		{
    	        
    	         System.err.println("** connection error in loadConnect: " + ex.getMessage() );
    	         ex.printStackTrace();
    	 	}	   
    	     
    }
    
   public static void disconnect()  {
	   try {
		   	// ������ �ݴ´�.
		   if( stmt != null ) stmt.close();
		   if( con != null ) con.close();
	   	} catch (SQLException ex ) {};    
   }

    
    public static ResultSet selectQuery(String sql) { 
 	   try {
 		   // Statement ���� 
 		   stmt = con.createStatement();
 		            
 			   
 		   rs = stmt.executeQuery(sql);  

 	   } catch( SQLException ex ) 	    {
 		   System.err.println("** SQL exec error in selectQuery() : " + ex.getMessage() );
 	   }
 			
 	   return rs;
 		
    }
    //joinŬ����/////////////////////////////////////////////////////////////////////////////
    public int join(User user) {
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserBirthday());
			pstmt.setString(5, user.getUserNumber());
			pstmt.setString(5, user.getUserAdress());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // �����ͺ��̽� ����
	}
    /////////////////////////////////////////////////////////////////////////////
    
    public static String loginPassword(String id) { 
  	   String a = null;
  	   try {
  		   
  		   String sql = "select password from user where id = '" + id + "';";
  		   // Statement ���� 
  		   stmt = con.createStatement();
  		            
  			   
  		   rs = stmt.executeQuery(sql);  
  		   
  		   while(rs.next()) {
  			   a = rs.getString("password");
  		   }
  		   

  	   } catch( SQLException ex ) 	    {
  		   System.err.println("** SQL exec error in selectQuery() : " + ex.getMessage() );
  	   }
	//return id;
  	   return a;
     }
    
    public static String loginId(String id) { 
   	   String a = null;
   	   try {
   		   
   		   String sql = "select id from user where id = '" + id + "';";
   		   // Statement ���� 
   		   stmt = con.createStatement();
   		            
   			   
   		   rs = stmt.executeQuery(sql);  
   		   
   		   while(rs.next()) {
   			   a = rs.getString("id");
   		   }
   		   

   	   } catch( SQLException ex ) 	    {
   		   System.err.println("** SQL exec error in selectQuery() : " + ex.getMessage() );
   	   }
 	//return id;
   	   return a;
      }
    
    public static int selectPrice(String store_name, String food) { 
   	   int a = 0;
   	   try {
   		   
   		   String sql = "select price from menu where store_name = '" + store_name + "' AND food = '" + food + "';";
   		   // Statement ���� 
   		   stmt = con.createStatement();
   		            
   			   
   		   rs = stmt.executeQuery(sql);  
   		   
   		   while(rs.next()) {
   			   a = rs.getInt("price");
   		   }
   		   

   	   } catch( SQLException ex ) 	    {
   		   System.err.println("** SQL exec error in selectQuery() : " + ex.getMessage() );
   	   }
 	//return id;
   	   return a;
      }
    
    public static String selectAddress(String id) { 
    	   String a = null;
    	   try {
    		   
    		   String sql = "select address from user where id = '" + id + "';";
    		   // Statement ���� 
    		   stmt = con.createStatement();
    		            
    			   
    		   rs = stmt.executeQuery(sql);  
    		   
    		   while(rs.next()) {
    			   a = rs.getString("address");
    		   }
    		   

    	   } catch( SQLException ex ) 	    {
    		   System.err.println("** SQL exec error in selectQuery() : " + ex.getMessage() );
    	   }
  	//return id;
    	   return a;
       }
    
    public static int selectMonth(String store_name, String month) { 
    	   int a = 0;
    	   try {
    		   
    		   String sql = "select COUNT(store_name) from ordering where = store_name = '" + store_name + "' AND month = '" + month + "';";
    		   // Statement ���� 
    		   stmt = con.createStatement();
    		            
    			   
    		   rs = stmt.executeQuery(sql);  
    		   
    		   while(rs.next()) {
    			   a = rs.getInt("COUNT(store_name)");
    		   }
    		   

    	   } catch( SQLException ex ) 	    {
    		   System.err.println("** SQL exec error in selectQuery() : " + ex.getMessage() );
    	   }
  	//return id;
    	   return a;
       }
    
    public static ResultSet writeReview(String id, String store_name, String content, int scope, int year, int month, int date) {
      	String sql = "insert into review values (  '" + id  + "', '" + store_name  + "','" + content  + "'," + scope  + "," + year  + "," + month  + ", " + date  + " ); ";
      	System.out.println("   >> SQL : " + sql + "\n");

      	int count = updateQuery(sql);
      	if (count==1)
      		return selectReview("" + scope);
      	else
      		return null;
      }

     

    private static ResultSet selectReview(String string) {
		// TODO Auto-generated method stub
		return null;

	}
    
    public static int updateQuery(String sql) { 
 	   int count;
 	   
 	   try {
 		   // Statement ���� 
 		   stmt = con.createStatement();
 		   count = stmt.executeUpdate(sql);  
 		   return count;

 	   } catch( SQLException ex ) 	    {
 		   System.err.println("** SQL exec error in updateQuery() : " + ex.getMessage() );
 		   return 0;
 	   }	
    }
    
    
    public static ResultSet selectAllStudent() {
 	   String sql = "select * from student;";
 	   System.out.println("   >> SQL : " + sql + "\n");

 	   return selectQuery(sql);
    }
    
    public static ResultSet selectUser(String id) {
 		   String sql = "select * from user where id = '" + id + "' ;";
 		   System.out.println("   >> SQL : " + sql + "\n");

 		   return selectQuery(sql);
    }
  
    public static ResultSet selectStudentOfDept(String dept) {
     	// �־��� dept�� ���� �л����� �˻�
     	String sql = "select sno, sname, year from student where dept = '"+dept+"';";
     	System.out.println("   >> SQL : " + sql + "\n");
 		   
     	return selectQuery(sql);
    }


     public static ResultSet selectNOStudentByDept() {
     	// �־��� dept�� ���� �л����� �˻�
     	String sql = "select dept as '�а�' , count(*) as '�л���' from student group by dept;";
     	System.out.println("   >> SQL : " + sql + "\n");
 		   
     	return selectQuery(sql);
     }
     
     public static Vector selectDistinctStore_name(String id) {
         String sql = "select store.store_name from store, ordering where store.store_name = ordering.store_name and id = '" + id + "';";
         System.out.println("   >> SQL : " + sql + "\n");

         ResultSet rs = selectQuery(sql);
         Vector store_name = new Vector();   
         String storename;

         try {
            while(rs.next()) {
               store_name.add(storename = rs.getString("store_name"));
               System.out.println("   >> SQL : store_name=" + storename + "\n");
            }
         } catch( SQLException ex ) {
            System.err.println("** SQL exec error in selectDistinctDept() : " + ex.getMessage() );      
         }
     return store_name;   
      }
     
     public static Vector RootReview()
     {
          String sql = "select distinct store_name from review;";
          System.out.println("   >> SQL : " + sql + "\n");

          ResultSet rs = selectQuery(sql);
          Vector store_name = new Vector();   
          String storename;

          try {
             while(rs.next()) {
                store_name.add(storename = rs.getString("store_name"));
                System.out.println("   >> SQL : store_name=" + storename + "\n");
             }
          } catch( SQLException ex ) {
             System.err.println("** SQL exec error in selectDistinctDept() : " + ex.getMessage() );      
          }   

          return store_name;
     }
     
     public static Vector RootOrder()
     {
          String sql = "select distinct store_name from ordering;";
          System.out.println("   >> SQL : " + sql + "\n");

          ResultSet rs = selectQuery(sql);
          Vector store_name = new Vector();   
          String storename;

          try {
             while(rs.next()) {
                store_name.add(storename = rs.getString("store_name"));
                System.out.println("   >> SQL : store_name=" + storename + "\n");
             }
          } catch( SQLException ex ) {
             System.err.println("** SQL exec error in selectDistinctDept() : " + ex.getMessage() );      
          }   

          return store_name;
     }
     
     public static ResultSet deleteOrdering(String id, String food) {
         String sql = "delete from ordering where id = '" + id + "' and food = '" + food + "';";
         System.out.println("   >> SQL : " + sql + "\n");

         int count = updateQuery(sql);

         if (count==1)
            return selectReview("");
         else
            return null;
     }
     
     public static ResultSet insertUser(String id, String password, String name, String address, String tel) {
      	// �־��� st��  Student ���̺� �����ϰ� ���Ե� ����� ���̺��� �˻��Ͽ� ��ȭ
      	String sql = "insert into user values ('" + id + "', '" + password + "', '" 
      	                                            + name + "', '" + address  + "','" + tel + "' ); ";
      	System.out.println("   >> SQL : " + sql + "\n");
  		   
      	int count = updateQuery(sql);
      	
      	if (count==1)
      		return selectUser("");
      	else
      		return null;
      }
     
     public static ResultSet insertstore(String store_name, String category, String tel) {
       	// �־��� st��  Student ���̺� �����ϰ� ���Ե� ����� ���̺��� �˻��Ͽ� ��ȭ
       	String sql = "insert into store values ('" + store_name + "', '" + category + "', " + 0 + ", '" + tel + "' ); ";
       	System.out.println("   >> SQL : " + sql + "\n");
   		   
       	int count = updateQuery(sql);
       	
       	if (count==1)
       		return selectUser("");
       	else
       		return null;
       }
     
     public static ResultSet insertmenu(String store_name, String food, String content, int price) {
        	// �־��� st��  Student ���̺� �����ϰ� ���Ե� ����� ���̺��� �˻��Ͽ� ��ȭ
        	String sql = "insert into menu values ('" + food + "', '" + store_name + "', " + price + ", '" + content + "'); ";
        	System.out.println("   >> SQL : " + sql + "\n");
    		   
        	int count = updateQuery(sql);
        	
        	if (count==1)
        		return selectUser("");
        	else
        		return null;
        }
     
     public static ResultSet insertordering(String food, String store_name, String id, int price, String address, String pay, String need, int year, int month, int day) {
     	// �־��� st��  Student ���̺� �����ϰ� ���Ե� ����� ���̺��� �˻��Ͽ� ��ȭ
     	String sql = "insert into ordering values ('" + id + "', '" + food + "', '" + store_name + "', '" + address + "', " + price + ", '" + pay + "', '" + need + "', " + year + ", " + month + ", " + day + "); ";
     	System.out.println("   >> SQL : " + sql + "\n");
 		   
     	int count = updateQuery(sql);
     	
     	if (count==1)
     		return selectUser("");
     	else
     		return null;
     }
     
     
     /*public static ResultSet insertStudent(Student st) {
     	// �־��� st��  Student ���̺� �����ϰ� ���Ե� ����� ���̺��� �˻��Ͽ� ��ȭ
     	String sql = "insert into student values ("+st.getSno()+",'"+st.getSname()+"', " 
     	                                            +st.getYear()+ ", '" + st.getDept() +"' ); ";
     	System.out.println("   >> SQL : " + sql + "\n");
 		   
     	int count = updateQuery(sql);
     	
     	if (count==1)
     		return selectStudent(""+st.getSno());
     	else
     		return null;
     }*/
     
     public static Vector selectDistinctDepts() {
     	// �־��� dept�� ���� �л����� �˻�
     	String sql = "select distinct dept from student;";
     	System.out.println("   >> SQL : " + sql + "\n");
 		   
     	ResultSet rs = selectQuery(sql);
     	Vector depts = new Vector();
     	String dept;
     	
     	try {
     		while(rs.next()) {
     			depts.add(dept=rs.getString("dept"));
     		   	System.out.println("   >> SQL : dept=" + dept + "\n");
    	}
  	   } catch( SQLException ex ) {
 		   System.err.println("** SQL exec error in selectDistinctDept() : " + ex.getMessage() );	   
 	   }	

     	return depts;
    }
}

