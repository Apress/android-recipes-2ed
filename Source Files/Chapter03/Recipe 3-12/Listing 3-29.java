public boolean isNetworkReachable() {
    ConnectivityManager mManager =
            (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo current = mManager.getActiveNetworkInfo();
    if(current == null) {
        return false;
    }
    return (current.getState() == NetworkInfo.State.CONNECTED);
}
