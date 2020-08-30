<?php 
//print PHP_INT_MAX;
//9223372036854775807（19位，2^63）
//类型[0-15] + unix时间毫秒[0-4294967295] + 自增序列号[0-1023] + 机器ID[0-1023]
//[4] + [32] + [10] + [10] = 56
$unixTime = genTime();
print $unixTime;
print PHP_EOL;
$number = generatorId(1, $unixTime, 1023, 1023);
print $number;
print PHP_EOL;
print genMask(1024, 10);

function generatorId($type ,$unixTime, $sequence, $machineId) {
    $ret = 0;
    $ret = $ret | $machineId;
    $ret = $ret | $sequence << 10;
    $ret = $ret | $unixTime << 20;
    $ret = $ret | $type << 52;
    return $ret;
}

function genTime() {
    //2020-01-01 00:00:00
    return time() - 1577808000;
}

function genMask($num, $bit) {
    //numer 1024 10000000000
    //bit 10
    //-1:            1111111111111111111111111111111111111111111111111111111111111111
    //-1 << 10:      1111111111111111111111111111111111111111111111111111110000000000
    //-1 ^ -1 << 10: 0000000000000000000000000000000000000000000000000000001111111111
    //1024:          0000000000000000000000000000000000000000000000000000010000000000
    //result:        0000000000000000000000000000000000000000000000000000000000000000
    return $num & (-1 ^ -1 << $bit);
}
