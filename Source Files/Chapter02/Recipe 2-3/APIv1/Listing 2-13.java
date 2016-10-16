public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    //Retrieve the button object
    Button myButton = (Button)findViewById(R.id.myButton);
    //Attach the listener
    myButton.setOnClickListener(clickListener);
}

//Listener object to handle the click events
View.OnClickListener clickListener = new View.OnClickListener() {
    public void onClick(View v) {
        //Code to handle the click event
    {
};
