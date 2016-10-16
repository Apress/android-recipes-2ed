protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //Request window features before setContentView
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.main);

    //Show the progress indicator
    setProgressBarIndeterminateVisibility(true);

    //Hide the progress indicator
 setProgressBarIndeterminateVisibility(false);
}
