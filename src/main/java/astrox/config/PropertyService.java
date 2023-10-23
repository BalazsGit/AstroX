package astrox.config;

//import signum.kit.entity.signumAddress;

public interface PropertyService {
    boolean getBoolean(Prop<Boolean> prop);
    int getInt(Prop<Integer> prop);
    long getLong(Prop<Long> prop);
    float getFloat(Prop<Float> prop);
    String getString(Prop<String> prop);
    String[] getStringList(Prop<String> prop);
   // signumAddress getsignumAddress(Prop<signumAddress> prop);

    void setProperty(String key, String value);

    void reload(String fileName);
}
