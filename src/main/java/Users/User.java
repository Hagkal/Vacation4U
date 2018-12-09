package Users;


import javafx.scene.image.Image;

/**
 * this is the user type class.
 */
public class User {
    private String name;
    private String lastName;
    private String password;
    static boolean resetPassword = false;
    private Image image;


    /** user constructor
     * @param name user name string
     * @param lastName user last name string
     * @param password user password string
     */
    public User(String name, String lastName , String password ,Image image){
        this.name = name;
        this.image = image;
        this.lastName = lastName;
        this.password = password;

    }
    public  Image GetImage(){
        return image;
    }

    public String GetName(){
        return name;
    }

    public String GetLastName(){
        return lastName;
    }

    public String getPassword(){
        return password;
    }

    public void SetPassword(String password){
        if(resetPassword)
            this.password = password;
        resetPassword = false;
    }



}

