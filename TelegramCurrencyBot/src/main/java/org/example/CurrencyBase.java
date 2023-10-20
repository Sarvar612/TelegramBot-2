
package org.example;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class CurrencyBase {

    @SerializedName("base")
    private String mBase;
    @SerializedName("date")
    private String mDate;
    @SerializedName("provider")
    private String mProvider;
    @SerializedName("rates")
    private Rates mRates;
    @SerializedName("terms")
    private String mTerms;
    @SerializedName("time_last_updated")
    private Long mTimeLastUpdated;
    @SerializedName("WARNING_UPGRADE_TO_V6")
    private String mWARNINGUPGRADETOV6;

    public String getBase() {
        return mBase;
    }

    public void setBase(String base) {
        mBase = base;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getProvider() {
        return mProvider;
    }

    public void setProvider(String provider) {
        mProvider = provider;
    }

    public Double getRates(String type) {
        Rates rates=new Rates();
        switch (type){
            case "RUB" -> {
                Double uzs = rates.getUZS();
                return uzs;
            }
            case "USD" -> {
                Long rub = rates.getRUB();
                return  (double)rub;
            }
        }
        return null;
    }

    public void setRates(Rates rates) {
        mRates = rates;
    }

    public String getTerms() {
        return mTerms;
    }

    public void setTerms(String terms) {
        mTerms = terms;
    }

    public Long getTimeLastUpdated() {
        return mTimeLastUpdated;
    }

    public void setTimeLastUpdated(Long timeLastUpdated) {
        mTimeLastUpdated = timeLastUpdated;
    }

    public String getWARNINGUPGRADETOV6() {
        return mWARNINGUPGRADETOV6;
    }

    public void setWARNINGUPGRADETOV6(String wARNINGUPGRADETOV6) {
        mWARNINGUPGRADETOV6 = wARNINGUPGRADETOV6;
    }

}
