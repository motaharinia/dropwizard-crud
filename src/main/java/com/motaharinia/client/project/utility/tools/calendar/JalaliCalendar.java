package com.motaharinia.client.project.utility.tools.calendar;


import com.motaharinia.client.project.utility.custom.customexception.utility.UtilityExceptionKeyEnum;
import com.motaharinia.client.project.utility.custom.customexception.utility.UtilityException;
import lombok.EqualsAndHashCode;

import java.util.*;

/**
 * @author https://github.com/motaharinia<br>
 * این کلاس برای تبدیل تاریخ میلادی و جلالی استفاده میشود
 */
@EqualsAndHashCode(callSuper = false)
public class JalaliCalendar extends Calendar {
    protected static final int[] gregorianDaysInMonth = {31, 28, 31, 30, 31, 30, 31,
            31, 30, 31, 30, 31};
    protected static final int[] jalaliDaysInMonth = {31, 31, 31, 31, 31, 31, 30, 30,
            30, 30, 30, 29};

    public static final int FARVARDIN = 0;
    public static final int ESFAND = 11;

    private TimeZone timeZone = TimeZone.getDefault();
    private boolean isTimeSeted = false;

    private static final int ONE_SECOND = 1000;
    private static final int ONE_MINUTE = 60 * ONE_SECOND;
    private static final int ONE_HOUR = 60 * ONE_MINUTE;
    static final int BCE = 0;
    static final int CE = 1;
    private GregorianCalendar cal;

    static final int[] MIN_VALUES = {BCE, 1, FARVARDIN, 1, 0, 1, 1, SATURDAY,
            1, AM, 0, 0, 0, 0, 0, -13 * ONE_HOUR, 0};

    static final int[] LEAST_MAX_VALUES = {CE, 292269054, ESFAND, 52, 4, 28,
            365, FRIDAY, 4, PM, 11, 23, 59, 59, 999, 14 * ONE_HOUR,
            20 * ONE_MINUTE};

    static final int[] MAX_VALUES = {CE, 292278994, ESFAND, 53, 6, 31, 366,
            FRIDAY, 6, PM, 11, 23, 59, 59, 999, 14 * ONE_HOUR, 2 * ONE_HOUR};

    public JalaliCalendar() {
        this(TimeZone.getDefault(), Locale.getDefault());
    }

    public JalaliCalendar(TimeZone zone, Locale aLocale) {

        super(zone, aLocale);
        timeZone = zone;
        Calendar calendar = Calendar.getInstance(zone, aLocale);

        YearMonthDate yearMonthDate = new YearMonthDate(calendar.get(YEAR),
                calendar.get(MONTH), calendar.get(DATE));
        yearMonthDate = gregorianToJalali(yearMonthDate);
        set(yearMonthDate.getYear(), yearMonthDate.getMonth(), yearMonthDate.getDate());
        complete();

    }

    public Date getGregorianDate(String jalaliDate) {
        String[] arr = jalaliDate.split("/");

        YearMonthDate jalali = new YearMonthDate(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]) - 1, Integer.parseInt(arr[2]));

        YearMonthDate gDate = jalaliToGregorian(jalali);

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(gDate.getYear(), gDate.getMonth(), gDate.getDate());

        return calendar.getTime();
    }

    public String getJalaliDate(Date gDate) {

        Calendar gCal = Calendar.getInstance();

        gCal.clear();

        gCal.setTime(gDate);
        int myYear = gCal.get(Calendar.YEAR);
        int myMonth = gCal.get(Calendar.MONTH);
        int myDay = gCal.get(Calendar.DAY_OF_MONTH);
        YearMonthDate gMyDate = new YearMonthDate(myYear, myMonth, myDay);

        YearMonthDate jDate = gregorianToJalali(gMyDate);

        return jDate.year + "/" + (jDate.month + 1) + "/" + jDate.date;
    }

    JalaliCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second, int millis) {
        super();

        this.set(YEAR, year);
        this.set(MONTH, month);
        this.set(DAY_OF_MONTH, dayOfMonth);

        if (hourOfDay >= 12 && hourOfDay <= 23) {

            this.set(AM_PM, PM);
            this.set(HOUR, hourOfDay - 12);
        } else {
            this.set(HOUR, hourOfDay);
            this.set(AM_PM, AM);
        }

        this.set(HOUR_OF_DAY, hourOfDay);
        this.set(MINUTE, minute);
        this.set(SECOND, second);

        this.set(MILLISECOND, millis);

        YearMonthDate yearMonthDate = jalaliToGregorian(new YearMonthDate(fields[1], fields[2], fields[5]));
        cal = new GregorianCalendar(yearMonthDate.getYear(), yearMonthDate.getMonth(), yearMonthDate.getDate(), hourOfDay, minute, second);
        time = cal.getTimeInMillis();

        isTimeSeted = true;
    }

    public static YearMonthDate gregorianToJalali(YearMonthDate gregorian) {

        checkMonth(gregorian.getMonth());

        int jalaliYear;
        int jalaliMonth;
        int jalaliDay;

        int gregorianDayNo;
        int jalaliDayNo;
        int jalaliNP;
        int i;

        gregorian.setYear(gregorian.getYear() - 1600);
        gregorian.setDate(gregorian.getDate() - 1);

        gregorianDayNo = 365 * gregorian.getYear()
                + (int) Math.floor((gregorian.getYear() + 3) / 4d)
                - (int) Math.floor((gregorian.getYear() + 99) / 100d)
                + (int) Math.floor((gregorian.getYear() + 399) / 400d);
        for (i = 0; i < gregorian.getMonth(); ++i) {
            gregorianDayNo += gregorianDaysInMonth[i];
        }

        if (gregorian.getMonth() > 1
                && ((gregorian.getYear() % 4 == 0 && gregorian.getYear() % 100 != 0) || (gregorian
                .getYear() % 400 == 0))) {
            ++gregorianDayNo;
        }

        gregorianDayNo += gregorian.getDate();

        jalaliDayNo = gregorianDayNo - 79;

        jalaliNP = (int) Math.floor(jalaliDayNo / 12053d);
        jalaliDayNo = jalaliDayNo % 12053;

        jalaliYear = 979 + 33 * jalaliNP + 4 * (jalaliDayNo / 1461);
        jalaliDayNo = jalaliDayNo % 1461;

        if (jalaliDayNo >= 366) {
            jalaliYear += (int) Math.floor((jalaliDayNo - 1) / 365d);
            jalaliDayNo = (jalaliDayNo - 1) % 365;
        }

        for (i = 0; i < 11 && jalaliDayNo >= jalaliDaysInMonth[i]; ++i) {
            jalaliDayNo -= jalaliDaysInMonth[i];
        }
        jalaliMonth = i;
        jalaliDay = jalaliDayNo + 1;

        return new YearMonthDate(jalaliYear, jalaliMonth, jalaliDay);
    }

    public static YearMonthDate jalaliToGregorian(YearMonthDate jalali) {
        checkMonth(jalali.getMonth());


        int gregorianYear;
        int gregorianMonth;
        int gregorianDay;

        int gregorianDayNo;
        int jalaliDayNo;
        int leap;

        int i;
        jalali.setYear(jalali.getYear() - 979);
        jalali.setDate(jalali.getDate() - 1);

        jalaliDayNo = 365 * jalali.getYear() + (jalali.getYear() / 33)
                * 8 + (int) Math.floor(((jalali.getYear() % 33) + 3) / 4d);
        for (i = 0; i < jalali.getMonth(); ++i) {
            jalaliDayNo += jalaliDaysInMonth[i];
        }

        jalaliDayNo += jalali.getDate();

        gregorianDayNo = jalaliDayNo + 79;

        gregorianYear = 1600 + 400 * (int) Math.floor(gregorianDayNo / 146097d); /*
         * 146097
         * =
         * 365
         * *
         * 400
         * +
         * 400
         * /
         * 4
         * -
         * 400
         * /
         * 100
         * +
         * 400
         * /
         * 400
         */
        gregorianDayNo = gregorianDayNo % 146097;

        leap = 1;
        if (gregorianDayNo >= 36525) /* 36525 = 365*100 + 100/4 */ {
            gregorianDayNo--;
            gregorianYear += 100 * (int) Math.floor(gregorianDayNo / 36524d); /*
             * 36524
             * =
             * 365
             * *
             * 100
             * +
             * 100
             * /
             * 4
             * -
             * 100
             * /
             * 100
             */
            gregorianDayNo = gregorianDayNo % 36524;

            if (gregorianDayNo >= 365) {
                gregorianDayNo++;
            } else {
                leap = 0;
            }
        }

        gregorianYear += 4 * (int) Math.floor(gregorianDayNo / 1461d); /*
         * 1461 =
         * 365*4 +
         * 4/4
         */
        gregorianDayNo = gregorianDayNo % 1461;

        if (gregorianDayNo >= 366) {
            leap = 0;

            gregorianDayNo--;
            gregorianYear += (int) Math.floor(gregorianDayNo / 365d);
            gregorianDayNo = gregorianDayNo % 365;
        }

        for (i = 0; gregorianDayNo >= gregorianDaysInMonth[i]
                + ((i == 1 && leap == 1) ? i : 0); i++) {
            gregorianDayNo -= gregorianDaysInMonth[i]
                    + ((i == 1 && leap == 1) ? i : 0);
        }
        gregorianMonth = i;
        gregorianDay = gregorianDayNo + 1;

        return new YearMonthDate(gregorianYear, gregorianMonth, gregorianDay);

    }

    public static int weekOfYear(int dayOfYear, int year) {
        switch (dayOfWeek(JalaliCalendar.jalaliToGregorian(new YearMonthDate(
                year, 0, 1)))) {
            case 2:
                dayOfYear++;
                break;
            case 3:
                dayOfYear += 2;
                break;
            case 4:
                dayOfYear += 3;
                break;
            case 5:
                dayOfYear += 4;
                break;
            case 6:
                dayOfYear += 5;
                break;
            case 7:
                dayOfYear--;
                break;
            default:
                break;
        }
        dayOfYear = (int) Math.floor(dayOfYear / 7d);
        return dayOfYear + 1;
    }

    public static int dayOfWeek(YearMonthDate yearMonthDate) {
        Calendar cal = new GregorianCalendar(yearMonthDate.getYear(), yearMonthDate.getMonth(), yearMonthDate.getDate());
        return cal.get(DAY_OF_WEEK);
    }

    public static boolean isLeepYear(int year) {
        // Algorithm from www.wikipedia.com
        return year % 33 == 1 || year % 33 == 5 || year % 33 == 9
                || year % 33 == 13 || year % 33 == 17 || year % 33 == 22
                || year % 33 == 26 || year % 33 == 30;
    }

    @Override
    protected void computeTime() {

        if (!isTimeSet && !isTimeSeted) {
            Calendar calendar = Calendar.getInstance(timeZone);
            if (!isSet(HOUR_OF_DAY)) {
                super.set(HOUR_OF_DAY, calendar.get(HOUR_OF_DAY));
            }
            if (!isSet(HOUR)) {
                super.set(HOUR, calendar.get(HOUR));
            }
            if (!isSet(MINUTE)) {
                super.set(MINUTE, calendar.get(MINUTE));
            }
            if (!isSet(SECOND)) {
                super.set(SECOND, calendar.get(SECOND));
            }
            if (!isSet(MILLISECOND)) {
                super.set(MILLISECOND, calendar.get(MILLISECOND));
            }
            if (!isSet(ZONE_OFFSET)) {
                super.set(ZONE_OFFSET, calendar.get(ZONE_OFFSET));
            }
            if (!isSet(DST_OFFSET)) {
                super.set(DST_OFFSET, calendar.get(DST_OFFSET));
            }
            if (!isSet(AM_PM)) {
                super.set(AM_PM, calendar.get(AM_PM));
            }

            if (internalGet(HOUR_OF_DAY) >= 12
                    && internalGet(HOUR_OF_DAY) <= 23) {
                super.set(AM_PM, PM);
                super.set(HOUR, internalGet(HOUR_OF_DAY) - 12);
            } else {
                super.set(HOUR, internalGet(HOUR_OF_DAY));
                super.set(AM_PM, AM);
            }

            YearMonthDate yearMonthDate = jalaliToGregorian(new YearMonthDate(internalGet(YEAR), internalGet(MONTH), internalGet(DAY_OF_MONTH)));
            calendar.set(yearMonthDate.getYear(), yearMonthDate.getMonth(), yearMonthDate.getDate(), internalGet(HOUR_OF_DAY), internalGet(MINUTE), internalGet(SECOND));
            time = calendar.getTimeInMillis();

        } else if (!isTimeSet && isTimeSeted) {
            if (internalGet(HOUR_OF_DAY) >= 12
                    && internalGet(HOUR_OF_DAY) <= 23) {
                super.set(AM_PM, PM);
                super.set(HOUR, internalGet(HOUR_OF_DAY) - 12);
            } else {
                super.set(HOUR, internalGet(HOUR_OF_DAY));
                super.set(AM_PM, AM);
            }
            cal = new GregorianCalendar();
            super.set(ZONE_OFFSET, timeZone.getRawOffset());
            super.set(DST_OFFSET, timeZone.getDSTSavings());
            YearMonthDate yearMonthDate = jalaliToGregorian(new YearMonthDate(
                    internalGet(YEAR), internalGet(MONTH),
                    internalGet(DAY_OF_MONTH)));
            cal.set(yearMonthDate.getYear(), yearMonthDate.getMonth(),
                    yearMonthDate.getDate(), internalGet(HOUR_OF_DAY),
                    internalGet(MINUTE), internalGet(SECOND));
            time = cal.getTimeInMillis();
        }
    }

    @Override
    public void set(int field, int value) {
        switch (field) {
            case DATE:
                super.set(field, 0);
                add(field, value);
                break;

            case MONTH:
                if (value > 11) {
                    super.set(field, 11);
                    add(field, value - 11);
                } else if (value < 0) {
                    super.set(field, 0);
                    add(field, value);
                } else {
                    super.set(field, value);
                }
                break;

            case DAY_OF_YEAR:
                if (isSet(YEAR) && isSet(MONTH) && isSet(DAY_OF_MONTH)) {
                    super.set(YEAR, internalGet(YEAR));
                    super.set(MONTH, 0);
                    super.set(DATE, 0);
                    add(field, value);
                } else {
                    super.set(field, value);
                }
                break;

            case WEEK_OF_YEAR:
                if (isSet(YEAR) && isSet(MONTH) && isSet(DAY_OF_MONTH)) {
                    add(field, value - get(WEEK_OF_YEAR));
                } else {
                    super.set(field, value);
                }
                break;

            case WEEK_OF_MONTH:
                if (isSet(YEAR) && isSet(MONTH) && isSet(DAY_OF_MONTH)) {
                    add(field, value - get(WEEK_OF_MONTH));
                } else {
                    super.set(field, value);
                }
                break;

            case DAY_OF_WEEK:
                if (isSet(YEAR) && isSet(MONTH) && isSet(DAY_OF_MONTH)) {
                    add(DAY_OF_WEEK, value % 7 - get(DAY_OF_WEEK));
                } else {
                    super.set(field, value);
                }
                break;

            case HOUR_OF_DAY:
            case HOUR:
            case MINUTE:
            case SECOND:
            case MILLISECOND:
            case ZONE_OFFSET:
            case DST_OFFSET:
                if (isSet(YEAR) && isSet(MONTH) && isSet(DATE) && isSet(HOUR)
                        && isSet(HOUR_OF_DAY) && isSet(MINUTE) && isSet(SECOND)
                        && isSet(MILLISECOND)) {
                    cal = new GregorianCalendar();
                    YearMonthDate yearMonthDate = jalaliToGregorian(new YearMonthDate(
                            internalGet(YEAR), internalGet(MONTH),
                            internalGet(DATE)));
                    cal.set(yearMonthDate.getYear(), yearMonthDate.getMonth(),
                            yearMonthDate.getDate(), internalGet(HOUR_OF_DAY),
                            internalGet(MINUTE), internalGet(SECOND));
                    cal.set(field, value);
                    yearMonthDate = gregorianToJalali(new YearMonthDate(
                            cal.get(YEAR), cal.get(MONTH), cal.get(DATE)));
                    super.set(YEAR, yearMonthDate.getYear());
                    super.set(MONTH, yearMonthDate.getMonth());
                    super.set(DATE, yearMonthDate.getDate());
                    super.set(HOUR_OF_DAY, cal.get(HOUR_OF_DAY));
                    super.set(MINUTE, cal.get(MINUTE));
                    super.set(SECOND, cal.get(SECOND));

                } else {
                    super.set(field, value);
                }
                break;


            default:
                super.set(field, value);

        }
    }

    @Override
    protected void computeFields() {
        boolean temp = isTimeSet;
        if (!areFieldsSet) {
            setMinimalDaysInFirstWeek(1);
            setFirstDayOfWeek(7);

            // Day_Of_Year
            int dayOfYear = 0;
            int index = 0;

            while (index < fields[2]) {
                dayOfYear += jalaliDaysInMonth[index++];
            }
            dayOfYear += fields[5];
            super.set(DAY_OF_YEAR, dayOfYear);
            // ***

            // Day_of_Week
            super.set(DAY_OF_WEEK,
                    dayOfWeek(jalaliToGregorian(new YearMonthDate(fields[1],
                            fields[2], fields[5]))));
            // ***

            // Day_Of_Week_In_Month
            if (0 < fields[5] && fields[5] < 8) {
                super.set(DAY_OF_WEEK_IN_MONTH, 1);
            }

            if (7 < fields[5] && fields[5] < 15) {
                super.set(DAY_OF_WEEK_IN_MONTH, 2);
            }

            if (14 < fields[5] && fields[5] < 22) {
                super.set(DAY_OF_WEEK_IN_MONTH, 3);
            }

            if (21 < fields[5] && fields[5] < 29) {
                super.set(DAY_OF_WEEK_IN_MONTH, 4);
            }

            if (28 < fields[5] && fields[5] < 32) {
                super.set(DAY_OF_WEEK_IN_MONTH, 5);
            }
            // ***

            // Week_Of_Year
            super.set(WEEK_OF_YEAR, weekOfYear(fields[6], fields[1]));
            // ***

            // Week_Of_Month
            super.set(WEEK_OF_MONTH, weekOfYear(fields[6], fields[1])
                    - weekOfYear(fields[6] - fields[5], fields[1]) + 1);
            //

            isTimeSet = temp;
        }
    }

    @Override
    public void add(int field, int amount) {

        if (field == MONTH) {
            amount += get(MONTH);
            add(YEAR, amount / 12);
            super.set(MONTH, amount % 12);
            if (get(DAY_OF_MONTH) > jalaliDaysInMonth[amount % 12]) {
                super.set(DAY_OF_MONTH, jalaliDaysInMonth[amount % 12]);
                if (get(MONTH) == 11 && isLeepYear(get(YEAR))) {
                    super.set(DAY_OF_MONTH, 30);
                }
            }
            complete();

        } else if (field == YEAR) {

            super.set(YEAR, get(YEAR) + amount);
            if (get(DAY_OF_MONTH) == 30 && get(MONTH) == 11
                    && !isLeepYear(get(YEAR))) {
                super.set(DAY_OF_MONTH, 29);
            }

            complete();
        } else {
            YearMonthDate yearMonthDate = jalaliToGregorian(new YearMonthDate(
                    get(YEAR), get(MONTH), get(DATE)));
            Calendar gc = new GregorianCalendar(yearMonthDate.getYear(),
                    yearMonthDate.getMonth(), yearMonthDate.getDate(),
                    get(HOUR_OF_DAY), get(MINUTE), get(SECOND));
            gc.add(field, amount);
            yearMonthDate = gregorianToJalali(new YearMonthDate(gc.get(YEAR),
                    gc.get(MONTH), gc.get(DATE)));
            super.set(YEAR, yearMonthDate.getYear());
            super.set(MONTH, yearMonthDate.getMonth());
            super.set(DATE, yearMonthDate.getDate());
            super.set(HOUR_OF_DAY, gc.get(HOUR_OF_DAY));
            super.set(MINUTE, gc.get(MINUTE));
            super.set(SECOND, gc.get(SECOND));
            complete();
        }

    }

    @Override
    public void roll(int field, boolean up) {
        roll(field, up ? +1 : -1);
    }

    @Override
    public void roll(int field, int amount) {
        if (amount == 0) {
            return;
        }

        if (field < 0 || field >= ZONE_OFFSET) {
            throw new IllegalArgumentException();
        }

        complete();

        int unit;
        switch (field) {
            case AM_PM:
                if (amount % 2 != 0) {
                    if (internalGet(AM_PM) == AM) {
                        fields[AM_PM] = PM;
                    } else {
                        fields[AM_PM] = AM;
                    }
                    if (get(AM_PM) == AM) {
                        super.set(HOUR_OF_DAY, get(HOUR));
                    } else {
                        super.set(HOUR_OF_DAY, get(HOUR) + 12);
                    }
                }
                break;

            case YEAR:
                super.set(YEAR, internalGet(YEAR) + amount);
                if (internalGet(MONTH) == 11 && internalGet(DAY_OF_MONTH) == 30
                        && !isLeepYear(internalGet(YEAR))) {
                    super.set(DAY_OF_MONTH, 29);
                }
                break;

            case MINUTE:
                unit = 60;
                int m = (internalGet(MINUTE) + amount) % unit;
                if (m < 0) {
                    m += unit;
                }
                super.set(MINUTE, m);
                break;

            case SECOND:
                unit = 60;
                int s = (internalGet(SECOND) + amount) % unit;
                if (s < 0) {
                    s += unit;
                }
                super.set(SECOND, s);
                break;

            case MILLISECOND:
                unit = 1000;
                int ms = (internalGet(MILLISECOND) + amount) % unit;
                if (ms < 0) {
                    ms += unit;
                }
                super.set(MILLISECOND, ms);
                break;


            case HOUR:
                super.set(HOUR, (internalGet(HOUR) + amount) % 12);
                if (internalGet(HOUR) < 0) {
                    fields[HOUR] += 12;
                }
                if (internalGet(AM_PM) == AM) {
                    super.set(HOUR_OF_DAY, internalGet(HOUR));
                } else {
                    super.set(HOUR_OF_DAY, internalGet(HOUR) + 12);
                }

                break;

            case HOUR_OF_DAY:
                fields[HOUR_OF_DAY] = (internalGet(HOUR_OF_DAY) + amount) % 24;
                if (internalGet(HOUR_OF_DAY) < 0) {
                    fields[HOUR_OF_DAY] += 24;
                }
                if (internalGet(HOUR_OF_DAY) < 12) {
                    fields[AM_PM] = AM;
                    fields[HOUR] = internalGet(HOUR_OF_DAY);
                } else {
                    fields[AM_PM] = PM;
                    fields[HOUR] = internalGet(HOUR_OF_DAY) - 12;
                }
                break;

            case MONTH:
                int mon = (internalGet(MONTH) + amount) % 12;
                if (mon < 0) {
                    mon += 12;
                }
                super.set(MONTH, mon);

                int monthLen = jalaliDaysInMonth[mon];
                if (internalGet(MONTH) == 11 && isLeepYear(internalGet(YEAR))) {
                    monthLen = 30;
                }
                if (internalGet(DAY_OF_MONTH) > monthLen) {
                    super.set(DAY_OF_MONTH, monthLen);
                }
                break;

            case DAY_OF_MONTH:
                unit = 29;
                get(MONTH);
                if (get(MONTH) <= 5) {
                    unit = 31;
                }
                if (6 <= get(MONTH) && get(MONTH) <= 10) {
                    unit = 30;
                }
                if ((get(MONTH) == 11) && (isLeepYear(get(YEAR)))) {
                    unit = 30;
                }
                int d = (get(DAY_OF_MONTH) + amount) % unit;
                if (d < 0) {
                    d += unit;
                }
                super.set(DAY_OF_MONTH, d);
                break;


            case WEEK_OF_YEAR:
                break;

            case DAY_OF_YEAR:
                unit = (isLeepYear(internalGet(YEAR)) ? 366 : 365);
                int dayOfYear = (internalGet(DAY_OF_YEAR) + amount) % unit;
                dayOfYear = (dayOfYear > 0) ? dayOfYear : dayOfYear + unit;
                int month = 0;
                int temp = 0;
                while (dayOfYear > temp) {
                    temp += jalaliDaysInMonth[month++];
                }
                super.set(MONTH, --month);
                super.set(DAY_OF_MONTH, jalaliDaysInMonth[internalGet(MONTH)]
                        - (temp - dayOfYear));
                break;

            case DAY_OF_WEEK:
                int index = amount % 7;
                if (index < 0) {
                    index += 7;
                }
                int i = 0;
                while (i != index) {
                    if (internalGet(DAY_OF_WEEK) == FRIDAY) {
                        add(DAY_OF_MONTH, -6);
                    } else {
                        add(DAY_OF_MONTH, +1);
                    }
                    i++;
                }
                break;


            default:
                throw new IllegalArgumentException();
        }

    }

    @Override
    public int getMinimum(int field) {
        return MIN_VALUES[field];
    }

    @Override
    public int getMaximum(int field) {
        return MAX_VALUES[field];
    }

    @Override
    public int getGreatestMinimum(int field) {
        return MIN_VALUES[field];
    }

    @Override
    public int getLeastMaximum(int field) {
        return LEAST_MAX_VALUES[field];
    }

    public static class YearMonthDate {

        public YearMonthDate(int year, int month, int date) {
            this.year = year;
            this.month = month;
            this.date = date;
        }

        private int year;
        private int month;
        private int date;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public String toString() {
            return getYear() + "/" + getMonth() + "/" + getDate();
        }
    }


    private static void checkMonth(int month) {
        if (month > 11 || month < -11) {
            throw new UtilityException(JalaliCalendar.class, UtilityExceptionKeyEnum.DATE_VALIDATION_FAILED, "");
        }
    }
}
