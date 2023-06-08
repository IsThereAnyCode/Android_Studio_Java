package assignment.checkdo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class Calendar extends Fragment {

    private MaterialCalendarView materialCalendarView;
    private EventDecorator eventDecorator;

    private CalendarDay selectedDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        materialCalendarView = (MaterialCalendarView) v.findViewById(R.id.calendarView);

        TextView showDate = v.findViewById(R.id.date);
        EditText whatTodo = v.findViewById(R.id.whatTodo);
        Button btnTodo = v.findViewById(R.id.addTodo);

        materialCalendarView.state().edit()
                .setMinimumDate(selectedDate.from(2000, 1, 1))
                .setMaximumDate(selectedDate.from(2100, 1, 1))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        materialCalendarView.setCurrentDate(selectedDate.today());

        eventDecorator = new EventDecorator();
        materialCalendarView.addDecorator(eventDecorator);

        // 캘린더 뷰에서 날짜 선택시 선택된 날짜 토스트 메시지 생성
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();

                String selectedDateStr = Year + "년 " + Month + "월 " + Day + "일";
                showDate.setText(selectedDateStr);

                selectedDate = date;
            }
        });

        btnTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedDate != null) {
                    eventDecorator.addDate(selectedDate);
                    materialCalendarView.invalidateDecorators();
                }
            }
        });


        // Inflate the layout for this fragment
        return v;//nflater.inflate(R.layout.fragment_calendar, container, false);
    }
}