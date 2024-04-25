package com.topnetwork.wallet.common.valid;

/**
 * @ClassName RegexStr
 * @Description
 * @Author bran
 * @Date 2020/5/28 17:33
 */
public class RegexStr {

    public static final String HTTP="^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
    
    /**
	 * 以太坊地址
	 */
	public static final String ETHADDRESS = "^0x[a-fA-F0-9]{40}$";
	
	 /**
     * HASH交易值
     */
    public static final String HASH = "^0x[0-9a-z]{64}$";
}
