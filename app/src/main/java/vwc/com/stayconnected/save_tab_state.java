package vwc.com.stayconnected;

public class save_tab_state {

    public static int ID = 0;

    public save_tab_state() {}

    public static int getState()
    {
        return ID;
    }

    public static void setTabState(int id)
    {
        ID = id;
    }
}
