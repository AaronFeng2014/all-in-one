--[[key:当前访问key refillTime : 访问时间  limit : 限制次数  interval ： 间隔时间，一般为60s]]
local key, refillTime, limit, interval = KEYS[1], tonumber(ARGV[1]), tonumber(ARGV[2]), tonumber(ARGV[3]);
local access = 0;
local lastRefillTime;
local tokens;
local exist = redis.call("EXISTS", key);
if (exist == 1) then
    --[[拿到上次清零时设置的时间lastRefillTime]]
    lastRefillTime = tonumber(redis.call("hget", key, "lastRefillTime"));

    --[[拿到当前剩余的访问次数]]
    tokens = tonumber(redis.call("hget", key, "tokensRemaining"));

    local diff = refillTime - lastRefillTime;
    --[[如果当前访问时间减去上次清零时间>interval值，那么重新设置访问时间以及次数]]
    if (diff > interval) then
        access = 1;
        redis.call("hmset", key, "lastRefillTime", refillTime, "tokensRemaining", limit - 1)
    else --[[否则判断次数是否大于限制值]]
        if (tokens > 0) then
            access = 1;
            redis.call("hset", key, "tokensRemaining", tokens - 1);
        else
            access = 0;
        end;
    end;
else --[[如果redis里面不存在这个key，那么设置访问时间以及次数]]
    redis.call("hmset", key, "lastRefillTime", refillTime, "tokensRemaining", limit - 1);
    access = 1;
end;
return access;
