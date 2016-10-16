protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //Request window features before setContentView
    requestWindowFeature(Window.FEATURE_PROGRESS);
    setContentView(R.layout.main);

    //Set the progress bar visibility
    setProgressBarVisibility(true);
    //Control progress value with setProgress
    setProgress(0);
    //Setting progress to 100% will cause it to disappear
    setProgress(10000);

}
