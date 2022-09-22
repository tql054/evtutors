//package com.intern.evtutors.adapters
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.Intent
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import android.widget.TextView
//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.core.view.isVisible
//import androidx.fragment.app.FragmentActivity
//import com.intern.evtutors.R
//import com.intern.evtutors.activities.DemoStream
//import com.intern.evtutors.models.Lesson
//import java.text.SimpleDateFormat
//import java.util.*
//
//class ScheduleAdapter(var activity: FragmentActivity?, var context: Context?, var data:MutableList<Lesson>): BaseAdapter() {
//    class ViewHolder(row:View) {
//        var time : TextView
//        var classTime: TextView
//        var classItem: ConstraintLayout
//        var timeEnd: TextView
//        var className: TextView
//        init {
//            time = row.findViewById(R.id.schedule_time_begin)
//            timeEnd = row.findViewById(R.id.schedule_time_end)
//            classTime = row.findViewById(R.id.class_time)
//            classItem = row.findViewById(R.id.class_item)
//            className = row.findViewById(R.id.class_name)
//
//        }
//    }
//    override fun getCount(): Int {
//        return data.size
//    }
//
//    override fun getItem(p0: Int): Any {
//        return data.get(p0)
//    }
//
//    override fun getItemId(p0: Int): Long {
//        return p0.toLong()
//    }
//
//    @SuppressLint("ResourceAsColor")
//    override fun getView(position: Int, convertView: View?, p2: ViewGroup?): View {
//        var view:View?
//        var viewHolder: ViewHolder
//        if(convertView===null) {
//            var layoutInflater:LayoutInflater=LayoutInflater.from(context)
//            view =  layoutInflater.inflate(R.layout.schedule_times, null)
//            viewHolder =  ViewHolder(view)
//            view.tag = viewHolder
//        } else {
//            view = convertView
//            viewHolder = convertView.tag as ViewHolder
//        }
//
//        var lesson:Lesson = data[position]
////        handle checking class is streaming or not (must fixed)
//        val startHour = SimpleDateFormat("yyyy-M-dd hh:mm:ss").parse(lesson.timeStart)
//        val endHour = SimpleDateFormat("yyyy-M-dd hh:mm:ss").parse(lesson.timeEnd)
//
//        viewHolder.time.text = startHour.hours.toString() + ":00"
//        viewHolder.timeEnd.text = endHour.hours.toString() + ":00"
//        viewHolder.className.text = lesson.channelName
//        viewHolder.classTime.isVisible = false //false when not in time
//
//        if(lesson.id == 1) { //Must fixed: Handled by timing condition below
//            viewHolder.classTime.isVisible = true
//            if(lesson.status == "0") viewHolder.classTime.text = "Start now!"
//            viewHolder.classItem.setOnClickListener {
//                val intent: Intent = Intent(context, DemoStream::class.java)
//                intent.putExtra("lesson", lesson)
//                activity?.startActivity(intent)
//            }
//        }
//
//        //considering: streaming status is 0 or 1
//
//////       handle onclick class to join the meeting
////        val date = Date()
////        if(date >= startHour) {
////            if(date <= endHour) {
////                viewHolder.classItem.setOnClickListener {
////                    val intent: Intent = Intent(context, DemoStream::class.java)
////                    intent.putExtra("lesson", lesson)
////                    activity?.startActivity(intent)
////                }
////                viewHolder.classTime.isVisible = true
////            } else {
////                //enable lesson item (constraint layout)
////            }
////        }
//
//        return view as View
//    }
//
//}
//
