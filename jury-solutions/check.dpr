(*--------------------------------------------------------------*)
(* ACM ICPC 2008-2009, NEERC                                    *)
(* Northern Subregional Contest                                 *)
(* St Petersburg, November 1, 2008                              *)
(*--------------------------------------------------------------*)
(* Problem F. Fenwick Tree                                      *)
(*                                                              *)
(* Original idea         Roman Satukov                          *)
(* Problem statement     Andrew Stankevich                      *)
(* Testset               Andrew Stankevich                      *)
(*--------------------------------------------------------------*)
(* Checker                                                      *)
(*                                                              *)
(* Author                as                                     *)
(*--------------------------------------------------------------*)
 
uses
	testlib, SysUtils;

const
	maxn = 100000;

var
	i, n, ja, pa: longint;
	x: int64;
	s: string;
	a, b, c: array [0..maxn] of int64;
	d: array [0..maxn] of extended;

begin
	n := inf.readlongint;
	for i := 1 to n do begin
		a[i] := inf.readlongint;
	end;

	ja := 0;
	for i := 1 to n do begin
		x := strtoint64(ans.readword(blanks, blanks));
		if x <> a[i] then
			inc(ja);
	end;

	pa := 0;
	for i := 1 to n do begin
		s := ouf.readword(blanks, blanks);
		try
			b[i] := strtoint64(s);
		except
			quit(_pe, format('integer expected, %s found', [s]));
		end;
		if b[i] <> a[i] then
			inc(pa);
	end;

	if pa > ja then
		quit(_wa, format('changed - expected %d, found: %d', [ja, pa]));

	{ 
	  We can have overflow here, but it is not bad. The problem is that b[i] can 
	  be self-fenwick up to 2^64. We retest in floating point to check that.
	}
	c := b;
	for i := 2 to n do begin
		c[i] := c[i] + c[i - 1];
	end;
	for i := n downto 1 do begin
		c[i] := c[i] - c[i and (i - 1)];
	end;

	for i := 1 to n do begin
		if c[i] <> b[i] then
			quit(_wa, format('array is not self-fenwick, a[%d] = %d, b[%d] = %d', [i, b[i], i, c[i]]));
	end;

	for i := 1 to n do begin
		d[i] := b[i];
	end;
	for i := 2 to n do begin
		d[i] := d[i] + d[i - 1];
	end;
	for i := n downto 1 do begin
		d[i] := d[i] - d[i and (i - 1)];
	end;

	for i := 1 to n do begin
		if abs(d[i] - b[i]) > 1e9 then
			quit(_wa, format('array is not self-fenwick, a[%d] = %d, b[%d] = %.0f', [i, b[i], i, d[i]]));
	end;

	if (pa < ja) then
		quit(_fail, format('changed - expected %d, found: %d', [ja, pa]));

	quit(_ok, format('changed %d elements', [pa]));
end.