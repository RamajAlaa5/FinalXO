/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeserver;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TableModel {
    
    //String player_id,player_name,score,status;
    int id,score,status;
    
    String name,password;
//    private IntegerProperty idProperty;
//    private StringProperty nameProperty;
//    private IntegerProperty scoreProperty;
//     private IntegerProperty statusProperty;
    
         private DataBaseConnection databaseConnection;
         PreparedStatement pst;
         Connection con;
         
    public TableModel(int id, String name, int score, int status) throws ClassNotFoundException{
//        databaseConnection = PlayersDataBase.getDatabaseInstance();
//        databaseConnection.openConnection();
        databaseConnection = new DataBaseConnection();
        this.id=id;
        this.name=name;
        this.score=score;
        this.status=status;
//        this.password=password;
    }
    
    public TableModel(){
        
    }
    
    
    public int getId(){
return id;
    }
     public void  setId(int id){
this.id=id;
    }
     

     
      public String getName(){
return name;
    }
     public void  setName(String name){
this.name=name;
    }
     
           public String getPassword(){
return password;
    }
     public void  setPassword(String password){
this.password=password;
    }
     

     public int getScore(){
        return  score;
    }
     public void  setScore(int score){
         this.score=score;
    }
     
 
     
      public int getStatus(){
return status;
    }
     public void  setStatus(int status){
              this.status = status;

    }
     
     	public void signUp(String name, String password) throws SQLException {
            try{
                pst = con.prepareStatement("insert into players (player_name , password,score,status) values ( ? , ? , 0 ,'0')"); //id , name , password,status, score
		pst.setString(1, name);
		pst.setString(2, password);
		int nRows = pst.executeUpdate();
		System.out.println(nRows+ " row affected");
		pst.close();
		con.close();
            }
            catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
    
     
     
     

     
     
     
   
}
