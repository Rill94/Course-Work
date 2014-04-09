package CRUD;

import beans.AlbumBean;
import beans.NewsBean;
import beans.SongBean;
import beans.UserBean;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD
{
    Connection conn = null;
    Statement stmt;
    ResultSet rs;


    public List<UserBean> Read()
    {
        List<UserBean> usrlist = new ArrayList<UserBean>();
        try
        {
            Connect cp = new Connect();
            DataSource ds = cp.createConnection();
            conn = ds.getConnection();
            stmt = conn.createStatement();
            System.out.println(stmt);
            String query = "SELECT * from user";
            rs = stmt.executeQuery(query);
            while (rs.next())
            {
                UserBean user = new UserBean();
                user.setLogin(rs.getString("Login"));
                user.setPassword(rs.getString("Password"));
                user.setEmail(rs.getString("Email"));
                user.setAddress(rs.getString("Address"));
                user.setBirthDate(rs.getDate("BirthDate"));
                user.setRegDate(rs.getDate("RegDate"));
                user.setGender(rs.getString("Gender"));
                usrlist.add(user);
            }

            for (int i = 0; i<usrlist.size(); i++)
            {
                System.out.println("Логин: "+usrlist.get(i).getLogin()+", Пароль: "
                        +usrlist.get(i).getPassword()+", E-mail: "+usrlist.get(i).getEmail());
            }
            stmt.close();
            rs.close();
            return usrlist;

        }
        catch (Exception e)
        {
            System.err.println("Exception: "+e.getMessage());
            return null;
        }

    }




    public boolean Create(UserBean user)
    {
        try
        {
            Connect cp = new Connect();
            DataSource ds = cp.createConnection();
            conn = ds.getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("SELECT * FROM User");
            rs.last();
            rs.moveToInsertRow();
            rs.updateString("Login", user.getLogin());
            rs.updateString("Password", user.getPassword());
            rs.updateString("Email", user.getEmail());
            rs.updateDate("RegDate", new Date(System.currentTimeMillis()));
            rs.updateDate("BirthDate", (Date) user.getBirthDate());
            rs.updateString("Gender", user.getGender());
            rs.updateString("Address", user.getAddress());
            rs.insertRow();
            System.out.println("Новый пользователь успешно добавлен");
            stmt.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }



    public UserBean Read(String login)
    {
        UserBean usr = new UserBean();
        try
        {
            Connect cp = new Connect();
            DataSource ds = cp.createConnection();
            conn = ds.getConnection();
            int i = 0;
            stmt = conn.createStatement();
            String query = "SELECT * from user";
            rs = stmt.executeQuery(query);
            while (rs.next())
            {
                if (rs.getString("Login").equals(login))
                {

                    usr.setLogin(rs.getString("Login"));
                    usr.setEmail(rs.getString("Email"));
                    usr.setPassword(rs.getString("Password"));
                    i++;
                }
            }
            if (i == 0) System.out.println("Данный пользователь не зарегистрирован в системе");
            stmt.close();
            rs.close();
            return usr;
        }
        catch (Exception e)
        {
            System.err.println("Exception: "+e.getMessage());
            return null;
        }
    }




        public boolean CreateSong(SongBean song)
        {
            try
            {
                Connect cp = new Connect();
                DataSource ds = cp.createConnection();
                conn = ds.getConnection();
                CRUD crud = new CRUD();
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                rs = stmt.executeQuery("SELECT * FROM song");
                rs.last();
                rs.moveToInsertRow();
                rs.updateString("Name", song.getName());
                if (!(crud.checkAlbum(song.getAlbum())==00))
                {
                    AlbumBean albumBean = new AlbumBean();
                    albumBean.setName(song.getAlbum());
                    crud.CreateAlbum(albumBean);
                }
                else
                {
                    rs.updateInt("idAlbum", crud.checkAlbum(song.getAlbum()));
                }
                rs.updateInt("Year", song.getYear());
                rs.updateString("Genre", song.getGenre());
                rs.insertRow();
                System.out.println("Новая песня добавлена");
                stmt.close();
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
    }

    public boolean checkUser(String login, String password)
    {
        try
        {
            Connect cp = new Connect();
            DataSource ds = cp.createConnection();
            conn = ds.getConnection();
            stmt = conn.createStatement();
            System.out.println(stmt);
            String query = "SELECT * from user";
            rs = stmt.executeQuery(query);
            while (rs.next())
            {
                if (rs.getString("Login").equals(login)&(rs.getString("Password").equals(password)))
                {
                    stmt.close();
                    rs.close();
                    return true;
                }
            }
            stmt.close();
            rs.close();
            return false;

        }
        catch (Exception e)
        {
            System.err.println("Exception: "+e.getMessage());
            return false;
        }
    }

    public boolean CreateAlbum(AlbumBean album)
    {
        try
        {
            Connect cp = new Connect();
            DataSource ds = cp.createConnection();
            conn = ds.getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("SELECT * FROM album");
            rs.last();
            rs.moveToInsertRow();
            rs.updateString("Name", album.getName());
            rs.updateString("Genre", album.getGenre());
            rs.updateTime("Duration", Time.valueOf((album.getDuration())));
            rs.updateInt("Year", album.getYear());
            rs.insertRow();
            System.out.println("Новый альбом");
            stmt.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public int checkAlbum(String name)
    {
        try
        {
            Connect cp = new Connect();
            DataSource ds = cp.createConnection();
            conn = ds.getConnection();
            stmt = conn.createStatement();
            System.out.println(stmt);
            String query = "SELECT * from album";
            rs = stmt.executeQuery(query);
            while (rs.next())
            {
                if (rs.getString("Name").equals(name))
                {
                    stmt.close();
                    rs.close();
                    return rs.getInt("idalbum");
                }
            }
            stmt.close();
            rs.close();
            return 00;

        }
        catch (Exception e)
        {
            System.err.println("Exception: "+e.getMessage());
            return 00;
        }
    }

    public ArrayList<NewsBean> ReadNews()
    {
        ArrayList<NewsBean> newslist = new ArrayList<NewsBean>();
        try
        {
            Connect cp = new Connect();
            DataSource ds = cp.createConnection();
            conn = ds.getConnection();
            stmt = conn.createStatement();
            System.out.println(stmt);
            String query = "SELECT * from news";
            rs = stmt.executeQuery(query);
            while (rs.next())
            {
                NewsBean news = new NewsBean();
                news.setNewstext(rs.getString("newstext"));
                news.setUrlvideo(rs.getString("urlvideo"));
                newslist.add(news);
            }
            stmt.close();
            rs.close();
            return newslist;

        }
        catch (Exception e)
        {
            System.err.println("Exception: "+e.getMessage());
            return null;
        }

    }

    public NewsBean ReadNew(int id)
    {
        NewsBean somenew = new NewsBean();
        try
        {
            Connect cp = new Connect();
            DataSource ds = cp.createConnection();
            conn = ds.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * from news";
            rs = stmt.executeQuery(query);
            while (rs.next())
            {
                if (rs.getInt("idnews")==id)
                {
                    somenew.setNewstext(rs.getString("newstext"));
                    somenew.setUrlvideo(rs.getString("urlvideo"));
                }
            }
            stmt.close();
            rs.close();
            return somenew;
        }
        catch (Exception e)
        {
            System.err.println("Exception: "+e.getMessage());
            return null;
        }
    }

}
//
//    @Override
//    public boolean Update(String login, String password)
//    {
//        return true;
//    }
//
//    @Override
//    public boolean Delete(String login)
//    {
//        return true;
//    }