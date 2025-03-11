/*
Design a database connection pool manager. 
For instance, if the database accepts a maximum of 5 connections concurrently, 
your manager should allow any code to request a connection, and if less than 5 
are currently in use, receive one right away. If no connection is available, 
show us your thoughts on best practices for handling that situation.
*/
import java.util.*;

class Connection {
    int portNumber;
    String ip;
    String appName;
    Collection(String appName) {
        this.appName = appName;
    }
}
public class q1 {
    /*
     db_connection: [bus:true,grocery:true,store:true,logout:true,save_file:true]
     Queue: [download_file, car,
     */
    public List<Connection> db_connection;
    public LinkedList<String> queue;

    public boolean checkIfEmptyConnection() {
        return db_connection.size() == 5 ? true : false;
    }

    public Connection makeConnection(String app) {
        if (checkIfEmptyConnection()) {
            db_connection.addLast(new Connection(app));
            return db_connection.getLast();
        }
        else {
            return null;
        }
    }


    public static void main(String [] args) {
        queue = new LinkedList<String>();
    }
}
