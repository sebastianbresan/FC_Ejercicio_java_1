package fc;

public class User {

    String email, name, username;

    public User(String email, String name, String username) {
        this.email = email;
        this.name = name;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
