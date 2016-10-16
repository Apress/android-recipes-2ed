protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //Request window features before setContentView
    requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
    setContentView(R.layout.main);

    //Set the layout resource to use for the custom title
    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);

}
