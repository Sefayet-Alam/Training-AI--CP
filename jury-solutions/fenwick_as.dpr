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
(* Solution                                                     *)
(*                                                              *)
(* Author                Andrew Stankevich                      *)
(*--------------------------------------------------------------*)
 
uses
	sysutils;

const
	maxn = 100000;

var
	n, i, j, k: longint;
	a, b: array [0..maxn] of int64;
	v: int64;

function h(x: longint): longint;
var
	r: longint;
begin
	r := 0;
	while x mod 2 = 0 do begin
		x := x div 2;
		r := r + 1;
	end;
	h := r;
end;

begin
	reset(input, 'fenwick.in');
	rewrite(output, 'fenwick.out');

	readln(n);
	assert((1 <= n) and (n <= maxn));
	for i := 1 to n do begin
		read(a[i]);
		assert ((-1000000000 <= a[i]) and (a[i] <= 1000000000));
	end;
	b := a;
	for i := 2 to n do begin
		b[i] := b[i] + b[i - 1];
	end;
	for i := n downto 1 do begin
		b[i] := b[i] - b[i and (i - 1)];
	end;

	i := 0;
	while (1 shl i <= n) do begin
		for j := 1 to n do begin
			if (j + 1 <= n) and (h(j + 1) = i) then begin
				k := j;
				v := a[j + 1] - b[j + 1];
				a[j] := a[j] + v;
				while (k <= n) do begin
					b[k] := b[k] + v;
					k := (k or (k - 1)) + 1;
				end;
			end;
		end;
		i := i + 1;
	end;

	for i := 1 to n do begin
		write(b[i]);
		if (i < n) then
			write(' ');
	end;
	writeln;
end.