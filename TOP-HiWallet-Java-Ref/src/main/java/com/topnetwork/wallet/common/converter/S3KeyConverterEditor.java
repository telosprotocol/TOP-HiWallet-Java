//package com.topnetwork.wallet.common.converter;
//
//import com.topnetwork.common.converter.SourceConverter;
//
//import com.gitee.magic.core.exception.ApplicationException;
//import com.gitee.magic.core.converter.editor.StringConverterEditor;
//
//
///**
// * @ClassName UploadKeyConverterEditor
// * @Description
// * @Author bran
// * @Date 2020/5/27 15:26
// */
//public class UploadKeyConverterEditor extends StringConverterEditor {
//
//    public UploadKeyConverterEditor(Class<?> prototype) {
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
//        return con.converter(converter(),null);
//    }
//
//
//
//}
