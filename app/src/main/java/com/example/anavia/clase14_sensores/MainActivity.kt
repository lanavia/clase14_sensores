package com.example.anavia.clase14_sensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SensorEventListener {

    var sensorManager:SensorManager?=null
    var sensorLuz:Sensor?=null
    var sensorProximidad:Sensor?=null


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }


    override fun onResume() {

        sensorManager?.registerListener(this, sensorLuz, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager?.registerListener(this, sensorProximidad, SensorManager.SENSOR_DELAY_NORMAL)
        super.onResume()
    }

    override fun onPause() {
       sensorManager?.unregisterListener(this)
        super.onPause()
    }

    override fun onSensorChanged(event: SensorEvent?){
        if(event?.sensor?.type == Sensor.TYPE_LIGHT){
            lblLuz.text = event.values[0].toString()
        }

        if(event?.sensor?.type == Sensor.TYPE_PROXIMITY){
            println(" esto es un mensaje " + event.values.size)
            lblProximidad.text = event.values[0].toString()
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager


       /* val sensores = sensorManager.getSensorList(Sensor.TYPE_ALL)
        for (i in 0 ..sensores.size-1){
            lblSensor.append(sensores.get(i).name + "\n")
        }*/

        if(sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)!= null)
        {
             sensorLuz = sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)

        }else{
            Toast.makeText(this,"Sensor de luz no disponible", Toast.LENGTH_SHORT).show()
        }

        if(sensorManager?.getDefaultSensor(Sensor.TYPE_PROXIMITY)!= null)
        {
            sensorProximidad = sensorManager?.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        }
        else{
            Toast.makeText(this,"Sensor de proximidad no disponible", Toast.LENGTH_SHORT).show()
        }
    }


}
