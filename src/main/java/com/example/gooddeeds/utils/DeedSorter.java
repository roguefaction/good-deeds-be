package com.example.gooddeeds.utils;

import com.example.gooddeeds.model.Deed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DeedSorter {
    public static List<Deed> currentDateFilter(List<Deed> listToFilter) throws ParseException {

        Iterator<Deed> deedIterator = listToFilter.iterator();

        Date currentDate = yesterday();

        while (deedIterator.hasNext()) {
            Deed currentDeed = deedIterator.next();
            Date deedDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentDeed.getDate());
            if (deedDate.compareTo(currentDate) < 0)
                deedIterator.remove();
        }

        return listToFilter;
    }

    public static Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
}
