package com.example.educat.Notes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteUtilities
{
    public static String dateFromLong (long time)
    {
        DateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy 'at' hh:mm aaa", Locale.UK);
        return format.format(new Date(time));
    }
}
