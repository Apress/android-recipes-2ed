protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //Request window features before setContentView
    requestWindowFeature(Window.FEATURE_LEFT_ICON);
    setContentView(R.layout.main);

    //Set the layout resource to use for the custom title
    setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.icon);
}
