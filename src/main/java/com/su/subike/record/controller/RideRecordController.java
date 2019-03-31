package com.su.subike.record.controller;

import com.su.subike.bike.service.BikeGeoService;
import com.su.subike.common.constants.Constants;
import com.su.subike.common.exception.SuBikeException;
import com.su.subike.common.resp.ApiResult;
import com.su.subike.common.rest.BaseController;
import com.su.subike.record.entity.RideContrail;
import com.su.subike.record.entity.RideRecord;
import com.su.subike.record.service.RideRecordService;
import com.su.subike.user.entity.UserElement;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("rideRecord")
@Slf4j
public class RideRecordController extends BaseController{

    @Autowired
    @Qualifier("rideRecordServiceImpl")
    private RideRecordService rideRecordService;
    @Autowired
    private BikeGeoService bikeGeoService;


    /**
     *@Description 查询骑行历史
     */
    @ApiOperation(value="骑行历史",notes = "骑行历史分页 每次传递用户界面最后一条数据的ID 下拉时候调用 每次十条数据",httpMethod = "GET")
    @ApiImplicitParam(name = "id",value = "最后一条数据ID",required = true,dataType = "Long")
    @RequestMapping("/list/{id}")
    public ApiResult<List<RideRecord>> listRideRecord(@PathVariable("id") Long lastId){

        ApiResult<List<RideRecord>> resp = new ApiResult<>();
        try {
            UserElement ue = getCurrentUser();
            List<RideRecord> list = rideRecordService.listRideRecord(ue.getUserId(),lastId);
            resp.setData(list);
            resp.setMessage("查询成功");
        } catch (SuBikeException e) {
            resp.setCode(e.getStatusCode());
            resp.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("Fail to query ride record ", e);
            resp.setCode(Constants.RESP_STATUS_INTERNAL_ERROR);
            resp.setMessage("内部错误");
        }

        return resp;
    }

    /**
     *@Description 查询骑行轨迹
     */
    @ApiOperation(value="骑行轨迹查询",notes = "骑行轨迹查询",httpMethod = "GET")
    @ApiImplicitParam(name = "recordNo",value = "骑行历史记录号",required = true,dataType = "Long")
    @RequestMapping("/contrail/{recordNo}")
    public ApiResult<RideContrail> rideContrail(@PathVariable("recordNo") String recordNo){

        ApiResult<RideContrail> resp = new ApiResult<>();
        try {
            UserElement ue = getCurrentUser();
            RideContrail contrail = bikeGeoService.rideContrail("ride_contrail",recordNo);
            resp.setData(contrail);
            resp.setMessage("查询成功");
        } catch (SuBikeException e) {
            resp.setCode(e.getStatusCode());
            resp.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("Fail to query ride record ", e);
            resp.setCode(Constants.RESP_STATUS_INTERNAL_ERROR);
            resp.setMessage("内部错误");
        }

        return resp;
    }

}
