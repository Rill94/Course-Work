package beans;

import CRUD.CRUD;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@ManagedBean
@SessionScoped
public class UserBean implements Serializable
{
    private int userID;
    private String login;
    private String password;
    private String email;
    private Date birthDate;
    private String address;
    private Date regDate;
    private String gender;
    private boolean isLogged = true;//false;
    private String role;
    private List<UserBean> users;
    private int age;
    private UserDataModel userModel;

    public UserBean[] getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(UserBean[] selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    private UserBean[] selectedUsers;

    public int getAge() {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public static Integer calculateAge(final Date birthday)
    {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(birthday);
        // include day of birth
        dob.add(Calendar.DAY_OF_MONTH, -1);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) <= dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

    public List<UserBean> getUsers()
    {
        CRUD crud = new CRUD();
        users = crud.Read();
        return users;
    }

    public String getRole() {
        return role == null ? "": role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isInRole()
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Object requestObject = context.getRequest();
        if (!(requestObject instanceof HttpServletRequest))
            return false;
        HttpServletRequest request = (HttpServletRequest) requestObject;
        return request.isUserInRole(role);
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserBean ()
    {
        users = new ArrayList<UserBean>();
        userModel = new UserDataModel(users);
    }

    public String successfulreg() throws Exception {
        CRUD crud = new CRUD();
        UserBean userBean = new UserBean();
        userBean.setLogin(login);
        userBean.setPassword(password);
        userBean.setEmail(email);
        userBean.setAddress(address);
        userBean.setBirthDate(birthDate);
        if (birthDate==null) throw new Exception("Дата рождения null");
        userBean.setGender(gender);

        if (crud.Create(userBean))
        {
            userBean.setLogged(true);
            return "successfulreg.xhtml";
        }
        else return null;
    }

    public String enter(String login, String password)
    {
        CRUD crud = new CRUD();
        if (crud.checkUser(login, password))
        {
            setLogged(true);
            return "main.xhtml";
        }
        else
        {
            setLogged(false);
            return "main.xhtml";
        }
    }

    public void exit()
    {
        setLogged(false);
    }

}
