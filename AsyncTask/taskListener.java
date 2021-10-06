
public interface TaskListener {
    public void onTaskStarted();
    public void onTaskFinished(String s);
    public void onBadRequest();
    public void onError(Exception ex);

}
