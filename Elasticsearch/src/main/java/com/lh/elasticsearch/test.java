package com.lh.elasticsearch;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/11/29  11:02
 */
public class test {
    private static String json = "{\"matnr\":\"000000010643593541\",\"zdqWerks\":\"DC04\",\"zdqLgort\":\"8HAA\",\"lifnr\":\"0010149051\",\"zzflag\":\"0\",\"atp\":13.0,\"atpa\":13.0,\"atpb\":13.0,\"atp_rp\":13.0,\"clabs\":13.0,\"cinsm\":0.0,\"cspem\":0.0,\"sspem\":0.0,\"meng1\":0.0,\"meng2\":0.0,\"zstockStatus\":\"1\",\"times\":\"20181128025600.3716130\",\"msgDate\":\"2018-11-28\",\"gdsNm\":\"东北领域秋田小町10kg\",\"brandCd\":\"81X8\",\"brandNm\":\"金道福地\",\"deptCd\":\"00031\",\"deptNm\":\"粮油休食\",\"l4GdsGroupCd\":\"R9006210\",\"l4GdsGroupDesc\":\"五常米\",\"vendorNm\":\"(粮油休食)五常市金稻福米业有限公司\",\"create_time\":\"20181128105603\",\"update_time\":\"20181128105603\",\"agencyFlag\":\"1\",\"pbVal\":55.0,\"verpr\":53.35,\"sellMoney\":693.5500000000001,\"ncyMoney\":0.0,\"poIn\":0.0,\"poMenge\":0.0,\"rpoOut\":0.0,\"stoIn\":0.0,\"stoOut\":0.0,\"soOut\":0.0,\"rvOut\":0.0,\"zdqLgortName\":\"物流8HAA门店常规\",\"zdqWerksName\":\"昆明苏宁小店库\",\"cmmdtyCategory\":\"1\",\"goodsPlace\":\"3\",\"districtId\":\"10022\",\"salesOrgCode\":\"5403\",\"cmmdtyHierrarchy\":\"0003181X8\",\"stockPartition\":\"2\",\"orderType\":\"NB\",\"l3GdsGroupCd\":\"R9724\",\"l3GdsGroupDesc\":\"大米\",\"l2GdsGroupCd\":\"R9670\",\"l2GdsGroupDesc\":\"粮油\",\"l1GdsGroupCd\":\"R9655\",\"l1GdsGroupDesc\":\"国产食品\",\"atpMoney\":693.55,\"atpBMoney\":693.55,\"zwmtMoney\":0.0,\"soOutMoney\":0.0,\"poInMoney\":0.0,\"stoInMoney\":0.0,\"tmRv\":0.0,\"ddRv\":0.0,\"qgRv\":0.0,\"fmRv\":0.0,\"othersRv\":0.0,\"operationChannel\":\"X\",\"districtNm\":\"\",\"gdsStoreType\":\"\"}";

    public void getString(String str){
        JSONObject json = JSONObject.fromObject(str);
        System.out.println(json);
    }
    public static void main(String[] args) {
        test te = new test();
        te.getString(json);



    }
}
