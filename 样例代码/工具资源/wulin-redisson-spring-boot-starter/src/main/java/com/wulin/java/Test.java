//package com.wulin.java;
//
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.function.ServerResponse;
//
//import java.util.function.Function;
//
///**
// * https://zhuanlan.zhihu.com/p/47563733
// * @author 武林
// * @date 2021/1/4 18:11
// */
//public class Test {
//
//    @Autowired
//    RedissonClient redisson;
//
//    @RequestMapping(value = "lock", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
//    public @ResponseBody
//    ServerResponse<RLock> lock(@RequestBody ServerRequest<LockReqBody> req) {
//        return callR(redisson -> {
//            RLock lock = redisson.getLock(req.getReqBody().getLockKey());
//            lock.lock(req.getReqBody().getTimeout(), req.getReqBody().getUnit());
//            return lock;
//        });
//    }
//    //省略部分代码
//    private <R> ServerResponse callR(Function<RedissonClient, R> function) {
//        ServerResponse dv = RespHelper.serverResponse(RespCodeService.ERROR, "");
//        try {
//            long startTime = System.currentTimeMillis();
//            dv = RespHelper.serverResponse(RespCodeService.SUCCESS, function.apply(redisson));
//            logger.info("CALLR METHOD USE TIME:{}", System.currentTimeMillis() - startTime);
//        } catch (Throwable e) {
//            System.out.println("callR error");
//        }
//        return dv;
//    }
//}
