
dbl area(vector<pt> &vin){ //not necessary convex
	int n = vin.size();
	dbl ret = 0.0;
	for(int i = 0; i < n; i++)
	ret += trap(vin[i], vin[(i+1)%n]);
	return fabs(ret);
}

dbl peri(vector<pt> &vin){ //not necessary convex
	int n = vin.size();
	dbl ret = 0.0;
	
	for(int i = 0; i < n; i++)
		ret += dist(vin[i], vin[(i+1)%n]);
	return ret;
}

dbl heronArea(dbl ab, dbl bc, dbl ca) {
  dbl s = 0.5 * ab+bc+ca;
  return sqrt(s) * sqrt(s - ab) * sqrt(s - bc) * sqrt(s - ca); 
}

dbl height(pt a, pt b, pt c){
	// height from $a$ to the line $b\rightarrow c$
	dbl s3 = dist(c, b);
	dbl ar=triarea(a,b,c);
	return(2.0*ar/s3);
}

 // CONVEX HULL
int sideSign(pt& p1, pt& p2, pt& p3){
	// which side is $p_3$ to the line $p_1\rightarrow p_2$? return: $1$ left, $0$ on, $-1$ right
	dbl sg = (p1.x-p3.x)*(p2.y-p3.y)-(p1.y - p3.y)*(p2.x-p3.x);
	if(fabs(sg)<EPS) return 0;
	if(sg>0) return 1;
	return -1;
}

bool better(pt& p1,pt& p2,pt& p3){
	// used by convec hull: from $p_3$, if $p_1$ is better than $p_2$
	dbl sg = (p1.y - p3.y)*(p2.x-p3.x)-(p1.x-p3.x)*(p2.y-p3.y);
	//watch range of the numbers
	if(fabs(sg)<EPS){
		if(dist(p3,p1) > dist(p3,p2))return true;
		else return false;
	}
	if(sg<0) return true;
	return false;
}

void convHull(vector<pt> vin, vector<pt>& vout){
	//vin is not pass by reference, since we will rotate it
	vout.clear();
	int n=vin.size();
	sort(vin.begin(),vin.end());
	pt stk[MAX_SIZE];
	int pstk, i;
	//hopefully more than 2 points
	stk[0] = vin[0]; stk[1] = vin[1];
	pstk = 2;
	for(i=2; i<n; i++){
		if(dist(vin[i], vin[i-1])<EPS) continue;
		while(pstk > 1 && better(vin[i], stk[pstk-1], stk[pstk-2]))
		pstk--;
		stk[pstk] = vin[i];
		pstk++;
	}
	
	for(i=0; i<pstk; i++) vout.push_back(stk[i]);
	for(i=0; i<n; i++){ //turn 180 degree
		vin[i].y = -vin[i].y;
		vin[i].x = -vin[i].x;
	}
	
	sort(vin.begin(), vin.end());
	
	stk[0] = vin[0];
	stk[1] = vin[1];
	pstk = 2;
	for(i=2; i<n; i++){
		if(dist(vin[i], vin[i-1])<EPS) continue;
		while(pstk > 1 && better(vin[i], stk[pstk-1], stk[pstk-2]))
			pstk--;
		stk[pstk] = vin[i];
		pstk++;
	}	
	for(i=1; i<pstk-1; i++){
		stk[i].x= -stk[i].x; //don’t forget rotate 180 d back.
		stk[i].y= -stk[i].y;
		vout.push_back(stk[i]);
	}
}

int isConvex(vector<pt>& v){
	// return: $0$ !convex, $1$ convex: $2$ convex with unnecesary pts,
	// does not work if the poly is self intersecting, compute
	//  convex hull of v, and see if both have the same area
	int i,j,k;
	int c1=0; int c2=0; int c0=0;
	int n=v.size();
	for(i=0;i<n;i++){
		j=(i+1)%n;
		k=(j+1)%n;
		int s=sideSign(v[i], v[j], v[k]);
		if(s==0) c0++;
		if(s>0) c1++;
		if(s<0) c2++;
	}
	if(c1 && c2) return 0;
	if(c0) return 2;
	return 1;
}

// polygon cut
vector<pt> polygonCut(const vector<pt>& poly, pt s, pt e) {
	vector<pt> res;
	for(int i=0;i<(int)poly.size();i++){
		pt cur = poly[i], prev = i ? poly[i-1] : poly.back();
		bool side = s.cross(e, cur) < 0;
		if (side != (s.cross(e, prev) < 0)) {
			res.emplace_back();
			intersection(s, e, cur, prev, res.back());
		}
		if (side)
			res.push_back(cur);
	}
	return res;
}
