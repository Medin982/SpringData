package ProductShop.Entites.DTO;

import java.util.List;

public class UsersCountDTO {
    private int usersCount;
    private List<UsersSoldProductDTO> users;

    public UsersCountDTO(List<UsersSoldProductDTO> usersSoldProductDTOS) {
        this.usersCount = usersSoldProductDTOS.size();
        this.users = usersSoldProductDTOS;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public List<UsersSoldProductDTO> getUsers() {
        return users;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public void setUsers(List<UsersSoldProductDTO> users) {
        this.users = users;
    }
}
