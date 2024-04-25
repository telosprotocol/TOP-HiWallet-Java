//package com.topnetwork.wallet.common.converter;
//
//import com.topnetwork.common.converter.SourceConverter;
//import com.gitee.magic.core.exception.ApplicationException;
//import com.gitee.magic.core.converter.editor.StringArrayConverterEditor;
//import com.gitee.magic.core.json.JSONArray;
//
//import java.lang.reflect.Array;
//
///**
// * @ClassName UploadKeyArrayConverterEditor
// * @Description
// * @Author bran
// * @Date 2020/5/27 15:26
// */
//public class UploadKeyArrayConverterEditor  extends StringArrayConverterEditor {
//
//    public UploadKeyArrayConverterEditor(Class<?> prototype) {
//        super(prototype);
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public Object getSource() {
//        SourceConverter<String> con;
//        try {
//            con = (SourceConverter<String>) Class.forName("com.common.converter.S3BuildUrlConverter").newInstance();
//        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//            throw new ApplicationException(e);
//        }
//        JSONArray array=new JSONArray(converter());
//        Object arrays =  Array.newInstance(String.class, array.length());
//        for(int i=0;i<array.length();i++) {
//            Array.set(arrays, i, con.converter(array.getString(i),null));
//        }
//        return arrays;
//
//    }
//
//
//
//}
