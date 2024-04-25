package com.topnetwork.wallet.common.valid;

import com.base.core.head.valid.UploadKeyArrayValid;

import com.gitee.magic.core.valid.ValidCheckCustom;

/**
 * @ClassName S3KeyArrayValid
 * @Description
 * @Author bran
 * @Date 2020/5/27 15:29
 */
public class S3KeyArrayValid  implements ValidCheckCustom {

    @Override
    public boolean check(Object value) {
//        JsonArray array=new JsonArray(String.valueOf(value));
//        try {
//            ValidCheckCustom valid=(ValidCheckCustom)Class.forName("com.common.valid.S3CustomCheck").newInstance();
//            for(int i=0;i<array.length();i++) {
//                if(!valid.check(array.getString(i))) {
//                    return false;
//                }
//            }
//            return true;
//        }catch(BusinessException e) {
//            throw e;
//        }catch(Exception e) {
//            return false;
//        }
    	UploadKeyArrayValid uav=new UploadKeyArrayValid();
    	return uav.check(value);
    }

}
