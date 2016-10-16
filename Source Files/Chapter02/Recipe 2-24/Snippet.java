public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ListView list = (ListView)findViewById(R.id.mylist);
    TextView empty = (TextView)findViewById(R.id.myempty);
    //Attach the reference
    list.setEmptyView(empty);

    //Continue adding adapters and data to the list

}
