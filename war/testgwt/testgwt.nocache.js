function testgwt(){var M='',nb='" for "gwt:onLoadErrorFn"',lb='" for "gwt:onPropertyErrorFn"',Y='"><\/script>',$='#',vb='&',Zb='.cache.html',ab='/',Mb='17BD1E513094CE3DA00E1E62071C75D9',Ob='1CDD8572193FF997330E62384F15C7EA',Pb='1EBDE996D5F87649A465D0390AAC2A21',Qb='24ED3ED518A8D7A6EC994E21B30BDAA8',Rb='271DE6CD2500EC9DE1C65B9B52C23CAD',Sb='47D5B3DE076F03BC3C9C3B7CF7571B93',Tb='7FB14CF49A6B21113086CC6052DEEF34',Ub='913B51BA6C7289CA1D9DFADA166D6E3B',Vb='9E86DEE2424CFE044F7C8923EF5FE8BB',fc='<script defer="defer">testgwt.onInjectionDone(\'testgwt\')<\/script>',X='<script id="',ib='=',_='?',kb='Bad handler "',Wb='CDD299D95BB17C9F2DEA3DE71DD6141A',Xb='DFF79D53E740A86A31430F6DC952A152',ec='DOMContentLoaded',Yb='F6CC52586050578B8BBF12B085043A83',Z='SCRIPT',yb='Unexpected exception in locale detection, using default: ',xb='_',wb='__gwt_Locale',W='__gwt_marker_testgwt',Nb='ar_SA',bb='base',Q='begin',P='bootstrap',db='clear.cache.gif',hb='content',tb='default',V='end',Gb='gecko',Hb='gecko1_8',R='gwt.codesvr=',S='gwt.hosted=',T='gwt.hybrid',$b='gwt/standard/standard.css',mb='gwt:onLoadErrorFn',jb='gwt:onPropertyErrorFn',gb='gwt:property',dc='head',Kb='hosted.html?testgwt',cc='href',Fb='ie6',Eb='ie8',ob='iframe',cb='img',pb="javascript:''",_b='link',Jb='loadExternalRefs',sb='locale',ub='locale=',eb='meta',rb='moduleRequested',U='moduleStartup',Db='msie',fb='name',Ab='opera',qb='position:absolute;width:0;height:0;border:none',ac='rel',Cb='safari',Lb='selectingPermutation',O='startup',bc='stylesheet',N='testgwt',Ib='unknown',zb='user.agent',Bb='webkit';var k=window,l=document,m=k.__gwtStatsEvent?function(a){return k.__gwtStatsEvent(a)}:null,n=k.__gwtStatsSessionId?k.__gwtStatsSessionId:null,o,p,q,r=M,s={},t=[],u=[],v=[],w,x;m&&m({moduleName:N,sessionId:n,subSystem:O,evtGroup:P,millis:(new Date).getTime(),type:Q});if(!k.__gwt_stylesLoaded){k.__gwt_stylesLoaded={}}if(!k.__gwt_scriptsLoaded){k.__gwt_scriptsLoaded={}}function y(){var b=false;try{var c=k.location.search;return (c.indexOf(R)!=-1||(c.indexOf(S)!=-1||k.external&&k.external.gwtOnLoad))&&c.indexOf(T)==-1}catch(a){}y=function(){return b};return b}
function z(){if(o&&p){var b=l.getElementById(N);var c=b.contentWindow;if(y()){c.__gwt_getProperty=function(a){return F(a)}}testgwt=null;c.gwtOnLoad(w,N,r);m&&m({moduleName:N,sessionId:n,subSystem:O,evtGroup:U,millis:(new Date).getTime(),type:V})}}
function A(){var e,f=W,g;l.write(X+f+Y);g=l.getElementById(f);e=g&&g.previousSibling;while(e&&e.tagName!=Z){e=e.previousSibling}function h(a){var b=a.lastIndexOf($);if(b==-1){b=a.length}var c=a.indexOf(_);if(c==-1){c=a.length}var d=a.lastIndexOf(ab,Math.min(c,b));return d>=0?a.substring(0,d+1):M}
;if(e&&e.src){r=h(e.src)}if(r==M){var i=l.getElementsByTagName(bb);if(i.length>0){r=i[i.length-1].href}else{r=h(l.location.href)}}else if(r.match(/^\w+:\/\//)){}else{var j=l.createElement(cb);j.src=r+db;r=h(j.src)}if(g){g.parentNode.removeChild(g)}}
function B(){var b=document.getElementsByTagName(eb);for(var c=0,d=b.length;c<d;++c){var e=b[c],f=e.getAttribute(fb),g;if(f){if(f==gb){g=e.getAttribute(hb);if(g){var h,i=g.indexOf(ib);if(i>=0){f=g.substring(0,i);h=g.substring(i+1)}else{f=g;h=M}s[f]=h}}else if(f==jb){g=e.getAttribute(hb);if(g){try{x=eval(g)}catch(a){alert(kb+g+lb)}}}else if(f==mb){g=e.getAttribute(hb);if(g){try{w=eval(g)}catch(a){alert(kb+g+nb)}}}}}}
function C(a,b){return b in t[a]}
function D(a){var b=s[a];return b==null?null:b}
function E(a,b){var c=v;for(var d=0,e=a.length-1;d<e;++d){c=c[a[d]]||(c[a[d]]=[])}c[a[e]]=b}
function F(a){var b=u[a](),c=t[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(x){x(a,d,b)}throw null}
var G;function H(){if(!G){G=true;var a=l.createElement(ob);a.src=pb;a.id=N;a.style.cssText=qb;a.tabIndex=-1;l.body.appendChild(a);m&&m({moduleName:N,sessionId:n,subSystem:O,evtGroup:U,millis:(new Date).getTime(),type:rb});a.contentWindow.location.replace(r+J)}}
u[sb]=function(){try{var b;var c=tb||tb;if(b==null){var d=location.search;var e=d.indexOf(ub);if(e>=0){var f=d.substring(e);var g=f.indexOf(ib)+1;var h=f.indexOf(vb);if(h==-1){h=f.length}b=f.substring(g,h)}}if(b==null){b=D(sb)}if(b==null){b=k[wb]}else{k[wb]=b||c}if(b==null){return c}while(!C(sb,b)){var i=b.lastIndexOf(xb);if(i==-1){b=c;break}else{b=b.substring(0,i)}}return b}catch(a){alert(yb+a);return tb}};t[sb]={ar_SA:0,'default':1};u[zb]=function(){var b=navigator.userAgent.toLowerCase();var c=function(a){return parseInt(a[1])*1000+parseInt(a[2])};if(b.indexOf(Ab)!=-1){return Ab}else if(b.indexOf(Bb)!=-1){return Cb}else if(b.indexOf(Db)!=-1){if(document.documentMode>=8){return Eb}else{var d=/msie ([0-9]+)\.([0-9]+)/.exec(b);if(d&&d.length==3){var e=c(d);if(e>=6000){return Fb}}}}else if(b.indexOf(Gb)!=-1){var d=/rv:([0-9]+)\.([0-9]+)/.exec(b);if(d&&d.length==3){if(c(d)>=1008)return Hb}return Gb}return Ib};t[zb]={gecko:0,gecko1_8:1,ie6:2,ie8:3,opera:4,safari:5};testgwt.onScriptLoad=function(){if(G){p=true;z()}};testgwt.onInjectionDone=function(){o=true;m&&m({moduleName:N,sessionId:n,subSystem:O,evtGroup:Jb,millis:(new Date).getTime(),type:V});z()};A();var I;var J;if(y()){if(k.external&&(k.external.initModule&&k.external.initModule(N))){k.location.reload();return}J=Kb;I=M}B();m&&m({moduleName:N,sessionId:n,subSystem:O,evtGroup:P,millis:(new Date).getTime(),type:Lb});if(!y()){try{E([tb,Hb],Mb);E([Nb,Ab],Ob);E([Nb,Eb],Pb);E([tb,Cb],Qb);E([Nb,Hb],Rb);E([tb,Fb],Sb);E([tb,Gb],Tb);E([tb,Eb],Ub);E([tb,Ab],Vb);E([Nb,Fb],Wb);E([Nb,Cb],Xb);E([Nb,Gb],Yb);I=v[F(sb)][F(zb)];J=I+Zb}catch(a){return}}var K;function L(){if(!q){q=true;if(!__gwt_stylesLoaded[$b]){var a=l.createElement(_b);__gwt_stylesLoaded[$b]=a;a.setAttribute(ac,bc);a.setAttribute(cc,r+$b);l.getElementsByTagName(dc)[0].appendChild(a)}z();if(l.removeEventListener){l.removeEventListener(ec,L,false)}if(K){clearInterval(K)}}}
if(l.addEventListener){l.addEventListener(ec,function(){H();L()},false)}var K=setInterval(function(){if(/loaded|complete/.test(l.readyState)){H();L()}},50);m&&m({moduleName:N,sessionId:n,subSystem:O,evtGroup:P,millis:(new Date).getTime(),type:V});m&&m({moduleName:N,sessionId:n,subSystem:O,evtGroup:Jb,millis:(new Date).getTime(),type:Q});l.write(fc)}
testgwt();