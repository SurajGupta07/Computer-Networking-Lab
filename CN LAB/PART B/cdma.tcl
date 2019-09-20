set ns [new Simulator]
set tf [open out.tr w]
set nf [open out.nam w]

$ns trace-all $tf
$ns namtrace-all $nf

set node(ms1) [$ns node]
set node(bs1) [$ns node]
set node(msc) [$ns node]
set node(bs2) [$ns node]
set node(ms2) [$ns node]

$ns duplex-link $node(ms1) $node(bs1) 1Mb 1ms DropTail
$ns duplex-link $node(bs1) $node(msc) 1Mb 10ms DropTail
$ns duplex-link $node(msc) $node(bs2) 1Mb 10ms DropTail
$ns duplex-link $node(bs2) $node(ms2) 1Mb 1ms DropTail

puts "Cell Topology"

$ns bandwidth $node(ms1) $node(bs1) 64kb simplex
$ns bandwidth $node(bs1) $node(ms1) 384kb simplex

$ns insert-delayer $node(ms1) $node(bs1) [new Delayer]
set tcp1 [$ns create-connection TCP $node(ms1) TCPSink $node(ms2) 0]
set ftp1 [$tcp1 attach-app FTP]
$ns at 0.1 "$ftp1 start"

proc stop { } {
	global node opt nf
	set sid [$node(ms1) id]
	set did [$node(bs1) id]
	puts $sid
	puts $did
	exec getrc -s $sid -d $did -f 0 out.tr | \
	   raw2xg -s 0.01 -m 100 -r > plot.xgr
	   
	exec getrc -s $did -d $sid -f 0 out.tr | \
	   raw2xg -a -s 0.01 -m 100 >> plot.xgr
	   
	exec ./xg2gp.awk plot.xgr
	exec xgraph -bb -tk -nl -m -x time -y packets plot.xgr &
	exit 0
	}
	
	$ns at 20 "stop"
	$ns run	   	   
