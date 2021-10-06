
public interface TaskListener {
    public void onTaskStarted();
    public void onTaskFinished();
    public void onBadRequest();
    public void onError(Exception ex);

}
