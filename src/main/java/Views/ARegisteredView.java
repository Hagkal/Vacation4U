package Views;

public abstract class ARegisteredView extends AView {

    public String _loggedUser;
    public boolean _manager;

    public abstract void prepareView(String username, boolean isManager);
}
