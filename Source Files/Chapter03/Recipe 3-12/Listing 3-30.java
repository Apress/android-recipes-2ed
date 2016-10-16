try {
    //Attempt to access network resource
    //May throw HttpResponseException or some other IOException on failure
} catch (Exception e) {
    if( !isNetworkReachable() ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("No Network Connection");
        builder.setMessage("The Network is unavailable. Please try your request again later.");
        builder.setPositiveButton("OK",null);
        builder.create().show();
    }
}
