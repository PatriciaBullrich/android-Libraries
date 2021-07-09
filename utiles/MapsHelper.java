package com.example.sitis.utiles;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsHelper {

   public static void ponerMarker(double lat, double lng, GoogleMap map){
       if(map!=null){
           resetMap(map);
           LatLng latitud = new LatLng(lat,lng);
           MarkerOptions nuevoMarker = new MarkerOptions();
           map.addMarker(nuevoMarker.position(latitud));
           map.moveCamera(CameraUpdateFactory.newLatLng(latitud));
           //map.animateCamera(CameraUpdateFactory.newLatLngZoom(latitud,2.5F));
       }
       else{
           CustomLog.log("Recibi un mapa nulo");
       }
   }

   public static void resetMap(GoogleMap map){
       map.clear();
       map.animateCamera(CameraUpdateFactory.zoomTo(map.getMinZoomLevel()));
   }

    public static float distanceInMeters (float lat_a, float lng_a, float lat_b, float lng_b )
    {
        double earthRadius = 3958.75;
        double latDiff = Math.toRadians(lat_b-lat_a);
        double lngDiff = Math.toRadians(lng_b-lng_a);
        double a = Math.sin(latDiff /2) * Math.sin(latDiff /2) +
                Math.cos(Math.toRadians(lat_a)) * Math.cos(Math.toRadians(lat_b)) *
                        Math.sin(lngDiff /2) * Math.sin(lngDiff /2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = earthRadius * c;

        int meterConversion = 1609;

        return new Float(distance * meterConversion).floatValue();
    }

}
