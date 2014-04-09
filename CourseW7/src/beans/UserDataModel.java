package beans;

import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;


/**
 * Created with IntelliJ IDEA.
 * User: Лера
 * Date: 19.12.13
 * Time: 2:58
 * To change this template use File | Settings | File Templates.
 */
import java.util.List;

public class UserDataModel extends ListDataModel<UserBean> implements SelectableDataModel<UserBean>
{
    public UserDataModel()
    {}

    public UserDataModel(List<UserBean> data)
    {super(data);}

    @Override
    public UserBean getRowData(String rowKey)
    {
        List<UserBean> users = (List<UserBean>)getWrappedData();
        for(UserBean user : users)
        {
            if(user.getLogin().equals(rowKey))
                return user;
        }
        return null;
    }

    @Override
    public Object getRowKey(UserBean user)
    {
        return user.getLogin();
    }
}
