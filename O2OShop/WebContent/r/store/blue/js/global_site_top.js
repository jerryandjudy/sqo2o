/* SVN.committedRevision=327869 */
(function(bC,bs){
	function bX(){
		if(!cc.isReady){
			try{
				bp.documentElement.doScroll("left");
			}catch(a){
				setTimeout(bX,1);return}cc.ready()
		}
	}  
 function ct(a,b){
	 b.src?cc.ajax({url:b.src,async:false,dataType:"script"}):cc.globalEval(b.text||b.textContent||b.innerHTML||"");
	 b.parentNode&&b.parentNode.removeChild(b);
	}
 function b3(h,d,b,a,g,i){
	 var f=h.length;
	 if(typeof d==="object"){
		 for(var e in d){
			 b3(h,e,d[e],a,g,b);
		}
	  return h;
     }
    if(b!==bs){
    	a=!i&&a&&cc.isFunction(b);
    	for(e=0;e<f;e++){
    		g(h[e],d,a?b.call(h[e],e,g(h[e],d)):b,i)
    	}
    	return h;
    }
   return f?g(h[0],d):bs
 }
 function K(){return(new Date).getTime();}
 function b4(){return false;}
 function b6(){return true;}
 function s(d,a,b){b[0].type=d;return cc.event.handle.apply(a,b)}
 function cd(e){
	 var g,i=[],l=[],k=arguments,m,j,f,b,a,h;
	 j=cc.data(this,"events");
	 if(!(e.liveFired===this||!j||!j.live||e.button&&e.type==="click")){
		 e.liveFired=this;
         var d=j.live.slice(0);
         for(b=0;b<d.length;b++){
        	 j=d[b];
        	 j.origType.replace(bv,"")===e.type?l.push(j.selector):d.splice(b--,1)
         }
         m=cc(e.target).closest(l,e.currentTarget);
         a=0;
         for(h=m.length;a<h;a++){
        	 for(b=0;b<d.length;b++){
        		 j=d[b];
        		 if(m[a].selector===j.selector){
        			 f=m[a].elem;l=null;
        			 if(j.preType==="mouseenter"||j.preType==="mouseleave"){
        				 l=cc(e.relatedTarget).closest(j.selector)[0]
        			 }
        			 if(!l||l!==f){i.push({elem:f,handleObj:j})}
        		 }
              }
        }
        a=0;
        for(h=i.length;a<h;a++){
        	m=i[a];
        	e.currentTarget=m.elem;
        	e.data=m.handleObj.data;
        	e.handleObj=m.handleObj;
        	if(m.handleObj.origHandler.apply(m.elem,k)===false){
        		g=false;break
        	}
        }
        return g
     }
}
 function A(a,b){
	 return"live."+(a&&a!=="*"?a+".":"")+b.replace(/\./g,"`").replace(/ /g,"&")
}
 function bF(a){
	 return !a||!a.parentNode||a.parentNode.nodeType===11
}
 function bO(d,a){
	 var b=0;
	 a.each(function(){
		 if(this.nodeName===(d[b]&&d[b].nodeName)){
			 var h=cc.data(d[b++]),g=cc.data(this,h);
             if(h=h&&h.events){
            	 delete g.handle;g.events={};
            	 for(var f in h){
            		 for(var e in h[f]){
            			 cc.event.add(this,f,h[f][e],h[f][e].data)
            		}
            	}
             }
          }
    });
}
 function aa(f,b,e){
	 var d,a,g;
	 b=b&&b[0]?b[0].ownerDocument||b[0]:bp;
	 if(f.length===1&&typeof f[0]==="string"&&f[0].length<512&&b===bp&&!cp.test(f[0])&&(cc.support.checkClone||!b8.test(f[0]))){
		 a=true;
		 if(g=cc.fragments[f[0]]){
			 if(g!==1){d=g}
		 }
	 }
	 if(!d){
		 d=b.createDocumentFragment();
		 cc.clean(f,b,d,e)
	 }
	 if(a){
		 cc.fragments[f[0]]=g?d:1
	}
	 return{fragment:d,cacheable:a}
}
 function P(d,a){
	 var b={};
	 cc.each(
			 cw.concat.apply([],cw.slice(0,a)),
			 function(){b[this]=d}
	 );
	 return b;
}
 function bx(a){return"scrollTo" in a&&a.document?a:a.nodeType===9?a.defaultView||a.parentWindow:false}
 var cc=function(a,b){return new cc.fn.init(a,b)},
	 co=bC.jQuery,
	 cu=bC.$,
	 bp=bC.document,bZ,
	 W=/^[^<]*(<[\w\W]+>)[^>]*$|^#([\w-]+)$/,bq=/^.[^:#\[\.,]*$/,
	 b5=/\S/,
	 bw=/^(\s|\u00A0)+|(\s|\u00A0)+$/g,
	 cn=/^<(\w+)\s*\/?>(?:<\/\1>)?$/,
	 cq=navigator.userAgent,
	 ba=false,
	 cv=[],
	 Q,
	 a0=Object.prototype.toString,
	 bn=Object.prototype.hasOwnProperty,
	 bz=Array.prototype.push,
	 cx=Array.prototype.slice,
	 X=Array.prototype.indexOf;
     cc.fn=cc.prototype={
    		 init:function(a,b){
    	            var d,e;
    	            if(!a){return this}
    	            if(a.nodeType){
    	            	this.context=this[0]=a;this.length=1;return this}
    	            if(a==="body"&&!b){
    	            	this.context=bp;
    	            	this[0]=bp.body;
    	            	this.selector="body";
    	            	this.length=1;return this
    	            }
    	            if(typeof a==="string"){
    	            	if((d=W.exec(a))&&(d[1]||!b)){
    	            		if(d[1]){
    	            			e=b?b.ownerDocument||b:bp;
    	            			if(a=cn.exec(a)){
    	            				if(cc.isPlainObject(b)){
    	            					a=[bp.createElement(a[1])];
    	            					cc.fn.attr.call(a,b,true)
    	            			    }else{
    	            				    a=[e.createElement(a[1])]
    	            			    }
    	            			}else{
    	            				a=aa([d[1]],[e]);
    	            				a=(a.cacheable?a.fragment.cloneNode(true):a.fragment).childNodes
    	            			}
    	            	       return cc.merge(this,a)
    	            	    }else{
    	            	    	if(b=bp.getElementById(d[2])){
    	            	    		if(b.id!==d[2]){return bZ.find(a)}
    	            	    		this.length=1;
    	            	    		this[0]=b
    	            	    	}
    	            	    	this.context=bp;
    	            	    	this.selector=a;return this
    	            	    }
    	            	}else{
    	            		if(!b&&/^\w+$/.test(a)){
    	            			this.selector=a;
    	            			this.context=bp;
    	            			a=bp.getElementsByTagName(a);
    	            			return cc.merge(this,a)
                            }else{
                            	return !b||b.jquery?(b||bZ).find(a):cc(b).find(a)
                            }
    	            	}
    	          }else{
    	        	  if(cc.isFunction(a)){return bZ.ready(a)}
    	          }
    	          if(a.selector!==bs){this.selector=a.selector;this.context=a.context}
    	          return cc.makeArray(a,this)
    	  },
    	 selector:"",
    	 jquery:"1.4.2",
    	 length:0,
    	 size:function(){return this.length},
    	 toArray:function(){return cx.call(this,0)},
    	 get:function(a){return a==null?this.toArray():a<0?this.slice(a)[0]:this[a]},
    	 pushStack:function(a,b,d){
    		 var e=cc();
    		 cc.isArray(a)?bz.apply(e,a):cc.merge(e,a);
    		 e.prevObject=this;
    		 e.context=this.context;
    		 if(b==="find"){
    			 e.selector=this.selector+(this.selector?" ":"")+d
    		}else{
    			if(b){e.selector=this.selector+"."+b+"("+d+")"}
    		}
    		 return e},
    	each:function(a,b){ return cc.each(this,a,b)},
    	ready:function(a){
    		    cc.bindReady();
    	       if(cc.isReady){
    	    	   a.call(bp,cc)
    	        }else{
    	        	cv&&cv.push(a)
    	        }
    	       return this
    	      },
    	eq:function(a){return a===-1?this.slice(a):this.slice(a,+a+1)},
    	first:function(){return this.eq(0)},
    	last:function(){return this.eq(-1)},
    	slice:function(){return this.pushStack(cx.apply(this,arguments),"slice",cx.call(arguments).join(","))},
    	map:function(a){return this.pushStack(cc.map(this,function(b,d){return a.call(b,d,b)}))},
    	end:function(){return this.prevObject||cc(null)},
    	push:bz,
    	sort:[].sort,
    	splice:[].splice
    };
     cc.fn.init.prototype=cc.fn;
     cc.extend=cc.fn.extend=function(){
    	              var h=arguments[0]||{},d=1,b=arguments.length,a=false,g,i,f,e;
    	              if(typeof h==="boolean"){a=h;h=arguments[1]||{};d=2}
    	              if(typeof h!=="object"&&!cc.isFunction(h)){h={}}
    	              if(b===d){h=this;--d}
    	              for(;d<b;d++){
    	            	  if((g=arguments[d])!=null){
    	            		  for(i in g){
    	            			  f=h[i];e=g[i];
    	            			  if(h!==e){if(a&&e&&(cc.isPlainObject(e)||cc.isArray(e))){f=f&&(cc.isPlainObject(f)||cc.isArray(f))?f:cc.isArray(e)?[]:{};h[i]=cc.extend(a,f,e)
                  }else{if(e!==bs){h[i]=e}}}}}}return h
     };
    cc.extend({
    	noConflict:function(a){bC.$=cu;if(a){bC.jQuery=co}return cc},
    	isReady:false,
    	ready:function(){
    		if(!cc.isReady){
    		if(!bp.body){return setTimeout(cc.ready,13)}
    		cc.isReady=true;
    		if(cv){for(var a,b=0;a=cv[b++];){a.call(bp,cc)}cv=null}
    		cc.fn.triggerHandler&&cc(bp).triggerHandler("ready")}
    		},
    	bindReady:function(){
    			if(!ba){ba=true;
    			if(bp.readyState==="complete"){return cc.ready()}
    			if(bp.addEventListener){
    				bp.addEventListener("DOMContentLoaded",Q,false);
    				bC.addEventListener("load",cc.ready,false)
    			}else{
    				if(bp.attachEvent){
    					bp.attachEvent("onreadystatechange",Q);
    					bC.attachEvent("onload",cc.ready);
    					var a=false;
    					try{a=bC.frameElement==null}catch(b){}
    					bp.documentElement.doScroll&&a&&bX()}}}
    	},
    	isFunction:function(a){return a0.call(a)==="[object Function]"},
    	isArray:function(a){return a0.call(a)==="[object Array]"},
        isPlainObject:function(a){
    		   if(!a||a0.call(a)!=="[object Object]"||a.nodeType||a.setInterval){return false}
    		   if(a.constructor&&!bn.call(a,"constructor")&&!bn.call(a.constructor.prototype,"isPrototypeOf")){return false}
    		   var b;for(b in a){}
    		   return b===bs||bn.call(a,b)
    	},
    	isEmptyObject:function(a){for(var b in a){return false}return true},
    	error:function(a){throw a},
        parseJSON:function(a){
    		   if(typeof a!=="string"||!a){return null}
    	       a=cc.trim(a);
    	       if(/^[\],:{}\s]*$/.test(a.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g,"@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,"]").replace(/(?:^|:|,)(?:\s*\[)+/g,""))){
	             return bC.JSON&&bC.JSON.parse?bC.JSON.parse(a):(new Function("return "+a))()
	            }else{
	            	cc.error("Invalid JSON: "+a)
	            }
	       },
	     noop:function(){},
	     globalEval:function(d){
	        	if(d&&b5.test(d)){
	        		var a=bp.getElementsByTagName("head")[0]||bp.documentElement,
	        		b=bp.createElement("script");
                    b.type="text/javascript";
                    if(cc.support.scriptEval){
                    	b.appendChild(bp.createTextNode(d))
                    }else{b.text=d}
                    a.insertBefore(b,a.firstChild);
                    a.removeChild(b)}
	      },
          nodeName:function(a,b){return a.nodeName&&a.nodeName.toUpperCase()===b.toUpperCase()},
          each:function(g,b,d){
                    	var a,f=0,h=g.length,e=h===bs||cc.isFunction(g);
                    	if(d){
                    		if(e){
                    			for(a in g){
                    				if(b.apply(g[a],d)===false){break}
                    			}
                    		}else{
                    			for(;f<h;){if(b.apply(g[f++],d)===false){break}}
                    		}
                    	}else{
                    		if(e){
                    			for(a in g){
                    				if(b.call(g[a],a,g[a])===false){break}
                    			}
                    		}else{
                    			for(d=g[0];f<h&&b.call(d,f,d)!==false;d=g[++f]){}
                    		}
                    	}
                    	return g
           },
          trim:function(a){return(a||"").replace(bw,"")},
           makeArray:function(a,b){
                    	b=b||[];
                    	if(a!=null){
                    		a.length==null||typeof a==="string"||cc.isFunction(a)||typeof a!=="function"&&a.setInterval?bz.call(b,a):cc.merge(b,a)
                    	}
                    	return b
           },
         inArray:function(a,b){
                    	if(b.indexOf){return b.indexOf(a)}
                    	for(var d=0,e=b.length;d<e;d++){if(b[d]===a){return d}}
                    	return -1
         },
         merge:function(e,b){
                    		var a=e.length,f=0;
                    		if(typeof b.length==="number"){
                    			for(var d=b.length;f<d;f++){e[a++]=b[f]}
                    		}else{
                    			for(;b[f]!==bs;){e[a++]=b[f++]}
                    		}
                    		e.length=a;return e
        },
       grep:function(f,b,e){
                	   for(var d=[],a=0,g=f.length;a<g;a++){
                		   !e!==!b(f[a],a)&&d.push(f[a])}
                	   return d
       },
      map:function(g,b,d){
                	   for(var a=[],f,h=0,e=g.length;h<e;h++){
                		   f=b(g[h],h,d);if(f!=null){a[a.length]=f}}
                	   return a.concat.apply([],a)
       },
     guid:1,
     proxy:function(d,a,b){
                	  if(arguments.length===2){
                		  if(typeof a==="string"){
                			  b=d;d=b[a];a=bs
                		  }else{
                			  if(a&&!cc.isFunction(a)){b=a;a=bs}
                		  }
                	  }
                	 if(!a&&d){
                		 a=function(){
                			 return d.apply(b||this,arguments)
                		 }
                	 }
                	if(d){
                		a.guid=d.guid=d.guid||a.guid||cc.guid++}return a
       },
        uaMatch:function(a){
                	 a=a.toLowerCase();
                	 a=/(webkit)[ \/]([\w.]+)/.exec(a)||/(opera)(?:.*version)?[ \/]([\w.]+)/.exec(a)||/(msie) ([\w.]+)/.exec(a)||!/compatible/.test(a)&&/(mozilla)(?:.*? rv:([\w.]+))?/.exec(a)||[];
                     return{browser:a[1]||"",version:a[2]||"0"}
      },
      browser:{}
      });
    cq=cc.uaMatch(cq);
    if(cq.browser){
    	cc.browser[cq.browser]=true;
    	cc.browser.version=cq.version
    }
    if(cc.browser.webkit){cc.browser.safari=true}
    if(X){
    	cc.inArray=function(a,b){return X.call(b,a)}
    }
    bZ=cc(bp);
    if(bp.addEventListener){
    	Q=function(){
    		bp.removeEventListener("DOMContentLoaded",Q,false);cc.ready()}
    }else{
    	if(bp.attachEvent){Q=function(){if(bp.readyState==="complete"){bp.detachEvent("onreadystatechange",Q);cc.ready()}}}
    }
    (function(){
    	cc.support={};
    	var f=bp.documentElement,h=bp.createElement("script"),
    	i=bp.createElement("div"),j="script"+K();
    	i.style.display="none";
    	i.innerHTML="   <link/><table></table><a href='/a' style='color:red;float:left;opacity:.55;'>a</a><input type='checkbox'/>";
    	var g=i.getElementsByTagName("*"),a=i.getElementsByTagName("a")[0];
        if(!(!g||!g.length||!a)){
        	cc.support={
        			leadingWhitespace:i.firstChild.nodeType===3,
        			tbody:!i.getElementsByTagName("tbody").length,
        			htmlSerialize:!!i.getElementsByTagName("link").length,
        			style:/red/.test(a.getAttribute("style")),
        			hrefNormalized:a.getAttribute("href")==="/a",opacity:/^0.55$/.test(a.style.opacity),
        			cssFloat:!!a.style.cssFloat,
        			checkOn:i.getElementsByTagName("input")[0].value==="on",
        			optSelected:bp.createElement("select").appendChild(bp.createElement("option")).selected,
        			parentNode:i.removeChild(i.appendChild(bp.createElement("div"))).parentNode===null,
        			deleteExpando:true,
        			checkClone:false,
        			scriptEval:false,
        			noCloneEvent:true,
        			boxModel:null
        	};
        	h.type="text/javascript";
        	try{
        		h.appendChild(bp.createTextNode("window."+j+"=1;"))
        	}catch(d){}
        	f.insertBefore(h,f.firstChild);
        	if(bC[j]){cc.support.scriptEval=true;delete bC[j]}
        	try{
        		delete h.test
        	}catch(e){
        		cc.support.deleteExpando=false
        	}
        	f.removeChild(h);
        	if(i.attachEvent&&i.fireEvent){
        		i.attachEvent("onclick",function b(){cc.support.noCloneEvent=false;i.detachEvent("onclick",b)});
        		i.cloneNode(true).fireEvent("onclick")
        	}
        	i=bp.createElement("div");
        	i.innerHTML="<input type='radio' name='radiotest' checked='checked'/>";
        	f=bp.createDocumentFragment();
        	f.appendChild(i.firstChild);
        	cc.support.checkClone=f.cloneNode(true).cloneNode(true).lastChild.checked;
        	cc(function(){
        		var k=bp.createElement("div");
        		k.style.width=k.style.paddingLeft="1px";
        		bp.body.appendChild(k);
        		cc.boxModel=cc.support.boxModel=k.offsetWidth===2;
        		bp.body.removeChild(k).style.display="none"
        	});
           f=function(m){
        	    	var k=bp.createElement("div");
        	    	m="on"+m;var l=m in k;
        	    	if(!l){
        	    		k.setAttribute(m,"return;");
        	    	l=typeof k[m]==="function"
                   }
        	    	return l
          };
          cc.support.submitBubbles=f("submit");
          cc.support.changeBubbles=f("change");
          f=h=i=g=a=null
      }
    })();
    cc.props={
    		"for":"htmlFor",
    		"class":"className",
    		readonly:"readOnly",
    		maxlength:"maxLength",
    		cellspacing:"cellSpacing",
    		rowspan:"rowSpan",
    		colspan:"colSpan",
    		tabindex:"tabIndex",
    		usemap:"useMap",
    		frameborder:"frameBorder"
    };
    var G="jQuery"+K(),cs=0,br={};
    cc.extend({
    	cache:{},
    	expando:G,
    	noData:{embed:true,object:true,applet:true},
    	data:function(e,b,a){
    		if(!(e.nodeName&&cc.noData[e.nodeName.toLowerCase()])){
    			e=e==bC?br:e;var f=e[G],d=cc.cache;
    			if(!f&&typeof b==="string"&&a===bs){return null}
    			f||(f=++cs);
    		    if(typeof b==="object"){
    		    	e[G]=f;d[f]=cc.extend(true,{},b)
    		    }else{
    		    	if(!d[f]){e[G]=f;d[f]={}}
    		    }
    		    e=d[f];
    		    if(a!==bs){e[b]=a}
    		    return typeof b==="string"?e[b]:e}},
       removeData:function(e,b){
    		    	if(!(e.nodeName&&cc.noData[e.nodeName.toLowerCase()])){
    		    		e=e==bC?br:e;
    		    		var a=e[G],
    		    		f=cc.cache,d=f[a];
    		    		if(b){
    		    			if(d){
    		    				delete d[b];
    		    				cc.isEmptyObject(d)&&cc.removeData(e)
    		    			}
    		    		}else{
    		    			if(cc.support.deleteExpando){
    		    				delete e[cc.expando]
    		    			}else{
    		    				e.removeAttribute&&e.removeAttribute(cc.expando)}delete f[a]
    		    		}
    		    	}
    		    }
    });
    cc.fn.extend({
    	data:function(a,b){
    	       if(typeof a==="undefined"&&this.length){
    	    	     return cc.data(this[0])
    	       }else{
    	    	   if(typeof a==="object"){
    	    		   return this.each(function(){cc.data(this,a)})
    	    	   }
    	      }
    	       var d=a.split(".");d[1]=d[1]?"."+d[1]:"";
    	       if(b===bs){
    	    	   var e=this.triggerHandler("getData"+d[1]+"!",[d[0]]);
    	    	   if(e===bs&&this.length){e=cc.data(this[0],a)}
    	    	   return e===bs&&d[1]?this.data(d[0]):e
    	    	}else{
    	    		return this.trigger("setData"+d[1]+"!",[d[0],b]).each(function(){cc.data(this,a,b)})
    	    	}
    	  },
    	  removeData:function(a){
    		  return this.each(function(){cc.removeData(this,a)})
    	  }
     });
    cc.extend({
    	queue:function(a,b,d){
    	if(a){
    		b=(b||"fx")+"queue";
            var e=cc.data(a,b);
            if(!d){return e||[]}
            if(!e||cc.isArray(d)){
            	e=cc.data(a,b,cc.makeArray(d))
            }else{e.push(d)}
            return e}
    	},
    	dequeue:function(a,b){
    		b=b||"fx";var d=cc.queue(a,b),e=d.shift();
    		if(e==="inprogress"){e=d.shift()}
    		if(e){
    			b==="fx"&&d.unshift("inprogress");
    		    e.call(a,function(){cc.dequeue(a,b)})
    		}
    	}
    });
    cc.fn.extend({
    	queue:function(a,b){
    	  if(typeof a!=="string"){b=a;a="fx"}
    	  if(b===bs){return cc.queue(this[0],a)}
    	  return this.each(function(){var d=cc.queue(this,a,b);a==="fx"&&d[0]!=="inprogress"&&cc.dequeue(this,a)})
        },
        dequeue:function(a){return this.each(function(){cc.dequeue(this,a)})},
        delay:function(a,b){
        	a=cc.fx?cc.fx.speeds[a]||a:a;b=b||"fx";
        	return this.queue(b,function(){var d=this;setTimeout(function(){cc.dequeue(d,b)},a)})},
        clearQueue:function(a){return this.queue(a||"fx",[])}
    });
    var bU=/[\n\t]/g,bS=/\s+/,V=/\r/g,bH=/href|src|style/,bo=/(button|input)/i,
    cr=/(button|input|object|select|textarea)/i,bV=/^(a|area)$/i,bb=/radio|checkbox/;
   cc.fn.extend({
	   attr:function(a,b){return b3(this,a,b,true,cc.attr)},
	   removeAttr:function(a){return this.each(function(){cc.attr(this,a,"");this.nodeType===1&&this.removeAttribute(a)})},
	   addClass:function(f){
		   if(cc.isFunction(f)){
			   return this.each(function(k){var l=cc(this);l.addClass(f.call(this,k,l.attr("class")))})
			}
		   if(f&&typeof f==="string"){
			   for(var h=(f||"").split(bS),i=0,j=this.length;i<j;i++){
				   var g=this[i];
				   if(g.nodeType===1){
					   if(g.className){
						   for(var a=" "+g.className+" ",d=g.className,e=0,b=h.length;e<b;e++){
							   if(a.indexOf(" "+h[e]+" ")<0){d+=" "+h[e]}
						    }
						   g.className=cc.trim(d)
					   }else{
						   g.className=f
					   }
				  }
				}
		}
		return this},
		removeClass:function(h){
			if(cc.isFunction(h)){
				return this.each(function(k){
					var j=cc(this);j.removeClass(h.call(this,k,j.attr("class")))})
			}
			if(h&&typeof h==="string"||h===bs){
				for(var d=(h||"").split(bS),b=0,a=this.length;b<a;b++){
					var g=this[b];
					if(g.nodeType===1&&g.className){
						if(h){
							for(var i=(" "+g.className+" ").replace(bU," "),f=0,e=d.length;f<e;f++){
								i=i.replace(" "+d[f]+" "," ")
							}
							g.className=cc.trim(i)}else{g.className=""}
					}
			   }
		}return this},
		toggleClass:function(a,b){
			var d=typeof a,e=typeof b==="boolean";
			if(cc.isFunction(a)){
				return this.each(function(f){
					var g=cc(this);
					g.toggleClass(a.call(this,f,g.attr("class"),b),b)})
			}
			return this.each(function(){
				if(d==="string"){
					for(var g,i=0,h=cc(this),f=b,j=a.split(bS);g=j[i++];){
						f=e?f:!h.hasClass(g);h[f?"addClass":"removeClass"](g)
					}
				}else{
					if(d==="undefined"||d==="boolean"){
						this.className&&cc.data(this,"__className__",this.className);
						this.className=this.className||a===false?"":cc.data(this,"__className__")||""}}}
		  )},
		hasClass:function(d){
			  d=" "+d+" ";
			  for(var a=0,b=this.length;a<b;a++){
				  if((" "+this[a].className+" ").replace(bU," ").indexOf(d)>-1){return true}
			  }
			  return false},
		val:function(h){
				  if(h===bs){
					  var d=this[0];
					  if(d){
						  if(cc.nodeName(d,"option")){ return(d.attributes.value||{}).specified?d.value:d.text}
						  if(cc.nodeName(d,"select")){
							  var b=d.selectedIndex,a=[],g=d.options;
						       d=d.type==="select-one";
						       if(b<0){return null}
						       var i=d?b:0;for(b=d?b+1:g.length;i<b;i++){
						    	   var f=g[i];if(f.selected){h=cc(f).val();if(d){return h}a.push(h)}
						       }
						       return a
						   }
						  if(bb.test(d.type)&&!cc.support.checkOn){return d.getAttribute("value")===null?"on":d.value}
						  return(d.value||"").replace(V,"")
						}return bs
				}
				var e=cc.isFunction(h);
				return this.each(function(k){
					var l=cc(this),m=h;
					if(this.nodeType===1){
						if(e){m=h.call(this,k,l.val())}
						if(typeof m==="number"){m+=""}
						if(cc.isArray(m)&&bb.test(this.type)){
							this.checked=cc.inArray(l.val(),m)>=0
						}else{
							if(cc.nodeName(this,"select")){
								var j=cc.makeArray(m);
								cc("option",this).each(function(){this.selected=cc.inArray(cc(this).val(),j)>=0});
								if(!j.length){
									this.selectedIndex=-1}
							    }else{this.value=m}
						}
					}
				})
		}});
   cc.extend({
	   attrFn:{val:true,css:true,html:true,text:true,data:true,width:true,height:true,offset:true},
	   attr:function(f,b,e,d){
		   if(!f||f.nodeType===3||f.nodeType===8){return bs}
		   if(d&&b in cc.attrFn){return cc(f)[b](e)}
		   d=f.nodeType!==1||!cc.isXMLDoc(f);
		   var a=e!==bs;b=d&&cc.props[b]||b;
		   if(f.nodeType===1){
			   var g=bH.test(b);
			   if(b in f&&d&&!g){
				   if(a){
					   b==="type"&&bo.test(f.nodeName)&&f.parentNode&&cc.error("type property can't be changed");
					   f[b]=e
				   }
				   if(cc.nodeName(f,"form")&&f.getAttributeNode(b)){
					   return f.getAttributeNode(b).nodeValue
				   }
				   if(b==="tabIndex"){
					   return(b=f.getAttributeNode("tabIndex"))&&b.specified?b.value:cr.test(f.nodeName)||bV.test(f.nodeName)&&f.href?0:bs
                   }
				   return f[b]
			}
			if(!cc.support.style&&d&&b==="style"){
				if(a){f.style.cssText=""+e}
				return f.style.cssText
			}
			a&&f.setAttribute(b,""+e);
			f=!cc.support.hrefNormalized&&d&&g?f.getAttribute(b,2):f.getAttribute(b);
			return f===null?bs:f
	  }
	  return cc.style(f,b,e)
	 }
	});
   var bv=/\.(.*)$/,cm=function(a){return a.replace(/[^\w\s\.\|`]/g,function(b){return"\\"+b})};
   cc.event={
		   add:function(e,f,j,m){
	           if(!(e.nodeType===3||e.nodeType===8)){
	        	   if(e.setInterval&&e!==bC&&!e.frameElement){e=bC}
	        	   var l,n;
	        	   if(j.handler){l=j;j=l.handler}
	        	   if(!j.guid){j.guid=cc.guid++}
	        	   if(n=cc.data(e)){
	        		   var k=n.events=n.events||{},
	        		   h=n.handle;
	        		   if(!h){
	        			   n.handle=h=function(){
	        			   return typeof cc!=="undefined"&&!cc.event.triggered?cc.event.handle.apply(h.elem,arguments):bs}
	        		   }
	        		   h.elem=e;f=f.split(" ");
	        		   for(var b,a=0,i;b=f[a++];){
	        			   n=l?cc.extend({},l):{handler:j,data:m};
	        			   if(b.indexOf(".")>-1){
	        				   i=b.split(".");
                               b=i.shift();
                               n.namespace=i.slice(0).sort().join(".")
                           }else{i=[];n.namespace=""}
	        			   n.type=b;n.guid=j.guid;
	        			   var d=k[b],g=cc.event.special[b]||{};
	        			   if(!d){
	        				   d=k[b]=[];
	        				   if(!g.setup||g.setup.call(e,m,i,h)===false){
	        					   if(e.addEventListener){
	        						   e.addEventListener(b,h,false)
	        						}else{
	        							e.attachEvent&&e.attachEvent("on"+b,h)
	        						}
	        				   }
	        				}
	        			   if(g.add){
	        				   g.add.call(e,n);
	        				   if(!n.handler.guid){
	        					   n.handler.guid=j.guid
	        				   }
	        			   }
	        			   d.push(n);
	        			   cc.event.global[b]=true
	        			  }
	        		     e=null
	        		    }}
	        },
	        global:{},
	        remove:function(e,f,h,l){
	        	if(!(e.nodeType===3||e.nodeType===8)){
	        		var j,o=0,n,b,m,p,k,d,g=cc.data(e),i=g&&g.events;
	        		if(g&&i){
	        			if(f&&f.type){h=f.handler;f=f.type}
	        		if(!f||typeof f==="string"&&f.charAt(0)==="."){
	        			f=f||"";
	        			for(j in i){cc.event.remove(e,j+f)}
	        		}else{
	        			for(f=f.split(" ");j=f[o++];){
	        				p=j;n=j.indexOf(".")<0;b=[];
	        				if(!n){
	        					b=j.split(".");
	        				    j=b.shift();
	        				    m=new RegExp("(^|\\.)"+cc.map(b.slice(0).sort(),cm).join("\\.(?:.*\\.)?")+"(\\.|$)")
                            }
	        				if(k=i[j]){
	        					if(h){
	        						p=cc.event.special[j]||{};
	        						for(a=l||0;a<k.length;a++){
	        							d=k[a];
	        							if(h.guid===d.guid){
	        							  if(n||m.test(d.namespace)){
	        								 l==null&&k.splice(a--,1);
	        								 p.remove&&p.remove.call(e,d)
	        							  }
	        							  if(l!=null){break}
	        						    }
	        					   }
	        					  if(k.length===0||l!=null&&k.length===1){
	        							if(!p.teardown||p.teardown.call(e,b)===false){
	        								J(e,j,g.handle)
	        							}
	        							delete i[j]
	        						}
	        					}else{
	        						for(var a=0;a<k.length;a++){
	        							d=k[a];
	        							if(n||m.test(d.namespace)){
	        								cc.event.remove(e,p,d.handler,a);
	        								k.splice(a--,1)
	        							}
	        						}
	        				   }
	        			 }
	        		}
	        		if(cc.isEmptyObject(i)){
	        			if(f=g.handle){f.elem=null}
	        			delete g.events;
	        			delete g.handle;
	        			cc.isEmptyObject(g)&&cc.removeData(e)
	        		}
	        	}
	        }}
	     },
	     trigger:function(e,g,i,h){
	        	var j=e.type||e;
	        	if(!h){
	        		e=typeof e==="object"?e[G]?e:cc.extend(cc.Event(j),e):cc.Event(j);
	        		if(j.indexOf("!")>=0){
	        			e.type=j=j.slice(0,-1);
	        			e.exclusive=true
	        		}
	        		if(!i){
	        			e.stopPropagation();
	        			cc.event.global[j]&&cc.each(cc.cache,
	        					function(){
	        				      this.events&&this.events[j]&&cc.event.trigger(e,g,this.handle.elem)
                                 })
                    }
	        		if(!i||i.nodeType===3||i.nodeType===8){return bs}
	        		e.result=bs;e.target=i;g=cc.makeArray(g);
	        		g.unshift(e)}e.currentTarget=i;
	        		(h=cc.data(i,"handle"))&&h.apply(i,g);
	        		h=i.parentNode||i.ownerDocument;
	        		try{
	        			if(!(i&&i.nodeName&&cc.noData[i.nodeName.toLowerCase()])){
	        				if(i["on"+j]&&i["on"+j].apply(i,g)===false){e.result=false}
	        				}
	        		}catch(b){}
	        		if(!e.isPropagationStopped()&&h){
	        			cc.event.trigger(e,g,h,true)
	        		}else{
	        			if(!e.isDefaultPrevented()){
	        				h=e.target;
	        				var k,f=cc.nodeName(h,"a")&&j==="click",a=cc.event.special[j]||{};
	        				if((!a._default||a._default.call(i,e)===false)&&!f&&!(h&&h.nodeName&&cc.noData[h.nodeName.toLowerCase()])){
	        					try{
	        						if(h[j]){
	        						  if(k=h["on"+j]){h["on"+j]=null}
	        						  cc.event.triggered=true;h[j]()
	        						 }
	        					}catch(d){}
	        				if(k){h["on"+j]=k}
	        				cc.event.triggered=false}
	        			}
	        		}
	    },
	   handle:function(g){
	        			var b,d,a,f;
	        			g=arguments[0]=cc.event.fix(g||bC.event);
                        g.currentTarget=this;
                        b=g.type.indexOf(".")<0&&!g.exclusive;
                        if(!b){
                        	d=g.type.split(".");
                            g.type=d.shift();
                            a=new RegExp("(^|\\.)"+d.slice(0).sort().join("\\.(?:.*\\.)?")+"(\\.|$)")
                        }
                        f=cc.data(this,"events");
                        d=f[g.type];
                        if(f&&d){
                        	d=d.slice(0);
                        	f=0;
                        	for(var h=d.length;f<h;f++){
                        		var e=d[f];
                        		if(b||a.test(e.namespace)){
                        			g.handler=e.handler;
                        			g.data=e.data;
                        			g.handleObj=e;
                        			e=e.handler.apply(this,arguments);
                        			if(e!==bs){
                        				g.result=e;
                        				if(e===false){
                        					g.preventDefault();
                        					g.stopPropagation()}
                        			}
                        			if(g.isImmediatePropagationStopped()){break}}}}return g.result
         },
        props:"altKey attrChange attrName bubbles button cancelable charCode clientX clientY ctrlKey currentTarget data detail eventPhase fromElement handler keyCode layerX layerY metaKey newValue offsetX offsetY originalTarget pageX pageY prevValue relatedNode relatedTarget screenX screenY shiftKey srcElement target toElement view wheelDelta which".split(" "),
        fix:function(a){
                      if(a[G]){return a}
                      var b=a;
                      a=cc.Event(b);
                      for(var d=this.props.length,e;d;){
                    	  e=this.props[--d];a[e]=b[e]
                      }
                      if(!a.target){
                    	  a.target=a.srcElement||bp
                      }
                      if(a.target.nodeType===3){a.target=a.target.parentNode}
                      if(!a.relatedTarget&&a.fromElement){
                    	  a.relatedTarget=a.fromElement===a.target?a.toElement:a.fromElement}
                      if(a.pageX==null&&a.clientX!=null){
                    	  b=bp.documentElement;d=bp.body;
                    	  a.pageX=a.clientX+(b&&b.scrollLeft||d&&d.scrollLeft||0)-(b&&b.clientLeft||d&&d.clientLeft||0);
                    	  a.pageY=a.clientY+(b&&b.scrollTop||d&&d.scrollTop||0)-(b&&b.clientTop||d&&d.clientTop||0)
                      }
                      if(!a.which&&(a.charCode||a.charCode===0?a.charCode:a.keyCode)){
                    	  a.which=a.charCode||a.keyCode
                      }
                      if(!a.metaKey&&a.ctrlKey){a.metaKey=a.ctrlKey}
                      if(!a.which&&a.button!==bs){a.which=a.button&1?1:a.button&2?3:a.button&4?2:0}
                      return a
       },
       guid:100000000,
       proxy:cc.proxy,
       special:{
                 ready:{setup:cc.bindReady,teardown:cc.noop},
                 live:{add:function(a){
                	 cc.event.add(this,a.origType,cc.extend({},a,{handler:cd}))},
                  remove:function(d){
                		 var a=true,b=d.origType.replace(bv,"");
                		 cc.each(cc.data(this,"events").live||[],
                				 function(){if(b===this.origType.replace(bv,"")){return a=false}});
                		 a&&cc.event.remove(this,d.origType,cd)}},
                  beforeunload:{
                			 setup:function(d,a,b){
                			 if(this.setInterval){this.onbeforeunload=b}
                			 return false},
                			 teardown:function(a,b){
                				 if(this.onbeforeunload===b){
                					 this.onbeforeunload=null}
                			 }}
        };
       var J=bp.removeEventListener?
    	   function(d,a,b){d.removeEventListener(a,b,false)}:
    	   function(d,a,b){d.detachEvent("on"+a,b)};
     cc.Event=function(a){
    	 if(!this.preventDefault){return new cc.Event(a)}
    	 if(a&&a.type){
    		 this.originalEvent=a;this.type=a.type
    	 }else{this.type=a}
    	 this.timeStamp=K();
    	 this[G]=true
      };
    cc.Event.prototype={
    			 preventDefault:function(){
    		          this.isDefaultPrevented=b6;
    		          var a=this.originalEvent;if(a){
    		        	  a.preventDefault&&a.preventDefault();
                          a.returnValue=false}
    		     },
                stopPropagation:function(){
    		    	 this.isPropagationStopped=b6;
    		    	 var a=this.originalEvent;
    		    	 if(a){
    		    		 a.stopPropagation&&a.stopPropagation();
    		    		 a.cancelBubble=true}
    		   },
    		   stopImmediatePropagation:function(){
    		    			 this.isImmediatePropagationStopped=b6;this.stopPropagation()
    		    },
    		   isDefaultPrevented:b4,
    		   isPropagationStopped:b4,
    		   isImmediatePropagationStopped:b4
    };
  var cf=function(d){
    		var a=d.relatedTarget;
    		try{
    			for(;a&&a!==this;){a=a.parentNode}
    			if(a!==this){d.type=d.data;cc.event.handle.apply(this,arguments)}
    		}catch(b){}
   },
   cb=function(a){a.type=a.data;cc.event.handle.apply(this,arguments)};
  cc.each({mouseenter:"mouseover",mouseleave:"mouseout"},
    					function(a,b){
    				           cc.event.special[a]={
    				        		      setup:function(d){cc.event.add(this,b,d&&d.selector?cb:cf,a)},
    				        		      teardown:function(d){cc.event.remove(this,b,d&&d.selector?cb:cf)}
    				        		    }
    });
   if(!cc.support.submitBubbles){
    				cc.event.special.submit={
    						setup:function(){
    					            if(this.nodeName.toLowerCase()!=="form"){
    					            	cc.event.add(this,"click.specialSubmit",
    					            	function(d){
    					            		var a=d.target,b=a.type;
                                            if((b==="submit"||b==="image")&&cc(a).closest("form").length){
                                            	return s("submit",this,arguments)
                                            }
                                         });
    					            	cc.event.add(this,"keypress.specialSubmit",
    					            	  function(d){
    					            		var a=d.target,b=a.type;
    					            		if((b==="text"||b==="password")&&cc(a).closest("form").length&&d.keyCode===13){
    					            			return s("submit",this,arguments)
    					            		}
    					            	})
    					            }else{return false}},
    					      teardown:function(){cc.event.remove(this,".specialSubmit")}
    		}
    }
    if(!cc.support.changeBubbles){
    			var cl=/textarea|input|select/i,bM,
    			bJ=function(d){
    				var a=d.type,b=d.value;
    			 if(a==="radio"||a==="checkbox"){
    				b=d.checked
    			 }else{
    				if(a==="select-multiple"){
    					b=d.selectedIndex>-1?cc.map(d.options,function(e){return e.selected}).join("-"):""
    				}else{
    					if(d.nodeName.toLowerCase()==="select"){b=d.selectedIndex}
    				}
    		    }return b
    		  },
    		  bW=function(e,b){
    			  var a=e.target,f,d;
    			  if(!(!cl.test(a.nodeName)||a.readOnly)){
    				  f=cc.data(a,"_change_data");
                      d=bJ(a);
                      if(e.type!=="focusout"||a.type!=="radio"){
                    	  cc.data(a,"_change_data",d)}
                      if(!(f===bs||d===f)){
                    	  if(f!=null||d){e.type="change";return cc.event.trigger(e,b,a)}}
                  }
    		};
          cc.event.special.change={
            		   filters:{
            	               focusout:bW,
            	               click:function(d){
            	                             var a=d.target,b=a.type;
            	                             if(b==="radio"||b==="checkbox"||a.nodeName.toLowerCase()==="select"){
            	                            	 return bW.call(this,d)}
            	                            },
            	                keydown:function(d){
            	                            	var a=d.target,b=a.type;
            	                            	if(d.keyCode===13&&a.nodeName.toLowerCase()!=="textarea"||d.keyCode===32&&(b==="checkbox"||b==="radio")||b==="select-multiple"){
            	                            		return bW.call(this,d)
            	                            	}},
            	                beforeactivate:function(a){
            	                            	a=a.target;
            	                            	cc.data(a,"_change_data",bJ(a))
            	                }},
            	       setup:function(){
            	                	if(this.type==="file"){return false}
            	                	for(var a in bM){
            	                		cc.event.add(this,a+".specialChange",bM[a])}
            	                	return cl.test(this.nodeName)},
            	       teardown:function(){
            	                		cc.event.remove(this,".specialChange");return cl.test(this.nodeName)}
       };
               bM=cc.event.special.change.filters
  }
  bp.addEventListener&&cc.each(
    				{focus:"focusin",blur:"focusout"},
    		function(d,a){
    			function b(e){e=cc.event.fix(e);e.type=a;return cc.event.handle.call(this,e)}
    		    cc.event.special[a]={
    		    		setup:function(){this.addEventListener(d,b,true)},
    		    		teardown:function(){this.removeEventListener(d,b,true)}
    		    		}
    		}
   );
   cc.each(["bind","one"],
    		function(a,b){
    			cc.fn[b]=function(h,f,d){
    				if(typeof h==="object"){
    					for(var g in h){
    						this[b](g,f,h[g],d)}
    					return this
    				}
    				if(cc.isFunction(f)){d=f;f=bs}
    				var e=b==="one"?cc.proxy(d,
    						function(j){
                                 cc(this).unbind(j,e);
                                 return d.apply(this,arguments)}
    				):d;
    				if(h==="unload"&&b!=="one"){
    					this.one(h,f,d)
    				}else{
    					g=0;
    					for(var i=this.length;g<i;g++){
    						cc.event.add(this[g],h,e,f)}
    				}return this
    			}
  });
   cc.fn.extend({
	   unbind:function(a,b){
    			if(typeof a==="object"&&!a.preventDefault){
    				for(var d in a){this.unbind(d,a[d])}
    			}else{
    				d=0;
    				for(var e=this.length;d<e;d++){
    					cc.event.remove(this[d],a,b)}
    		}return this
    	},
    	delegate:function(a,b,d,e){return this.live(b,d,e,a)},
        undelegate:function(d,a,b){return arguments.length===0?this.unbind("live"):this.die(a,null,b,d)},
    	trigger:function(a,b){return this.each(function(){cc.event.trigger(a,b,this)})},
    	triggerHandler:function(a,b){
    			if(this[0]){
    				a=cc.Event(a);a.preventDefault();
    				a.stopPropagation();
    				cc.event.trigger(a,b,this[0]);
    				return a.result
    	}},
    	toggle:function(d){
    				for(var a=arguments,b=1;b<a.length;){
    					cc.proxy(d,a[b++])
    				}
    				return this.click(
    						cc.proxy(d,function(f){
    							var e=(cc.data(this,"lastToggle"+d.guid)||0)%b;cc.data(this,"lastToggle"+d.guid,e+1);f.preventDefault();return a[e].apply(this,arguments)||false}
    						))
    	},
    	hover:function(a,b){return this.mouseenter(a).mouseleave(b||a)}
   });
  var bQ={focus:"focusin",blur:"focusout",mouseenter:"mouseover",mouseleave:"mouseout"};
  cc.each(["live","die"],
    function(a,b){
        	cc.fn[b]=function(g,i,h,l){
        		var j,e=0,d,k,m=l||this.selector,f=l?this:cc(this.context);
        		if(cc.isFunction(i)){h=i;i=bs}
        		for(g=(g||"").split(" ");(j=g[e++])!=null;){
        			l=bv.exec(j);d="";
        			if(l){d=l[0];j=j.replace(bv,"")}
        			if(j==="hover"){
        				g.push("mouseenter"+d,"mouseleave"+d)
        			}else{
        				k=j;
        				if(j==="focus"||j==="blur"){
        					g.push(bQ[j]+d);j+=d
        				}else{j=(bQ[j]||j)+d}
        				b==="live"?f.each(function(){
        					cc.event.add(this,A(j,m),{data:i,selector:m,handler:h,origType:j,origHandler:h,preType:k})})
        					:f.unbind(A(j,m),h)
        			}
        		}
        		return this
        	}
        });
      cc.each("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error".split(" "),
    		  function(a,b){cc.fn[b]=function(d){return d?this.bind(b,d):this.trigger(b)};
    		  if(cc.attrFn){cc.attrFn[b]=true}}
      );
      bC.attachEvent&&!bC.addEventListener&&bC.attachEvent("onunload",
    		  function(){
    	            for(var a in cc.cache){
    	            	if(cc.cache[a].handle){try{cc.event.remove(cc.cache[a].handle.elem)}catch(b){}}
    	            }
    	           });
     (function(){
    	 function b(x){
    		 for(var y="",v,u=0;x[u];u++){
    			 v=x[u];
    			 if(v.nodeType===3||v.nodeType===4){
    				 y+=v.nodeValue
    			 }else{
    				 if(v.nodeType!==8){y+=b(v.childNodes)}
    			}
    		}
    		return y
    	}
    	function d(v,x,y,z,C,B){
    		C=0;for(var E=z.length;C<E;C++){
    			var D=z[C];
    			if(D){D=D[v];
    			for(var u=false;D;){
    				if(D.sizcache===y){u=z[D.sizset];break}
    				if(D.nodeType===1&&!B){D.sizcache=y;D.sizset=C}
    				if(D.nodeName.toLowerCase()===x){u=D;break}
    				D=D[v]}z[C]=u}
    			}
    	}
    	function e(v,x,y,z,C,B){
    		C=0;
    		for(var E=z.length;C<E;C++){
    			var D=z[C];if(D){D=D[v];for(var u=false;D;){
    				if(D.sizcache===y){u=z[D.sizset];break}if(D.nodeType===1){
    					if(!B){
    					D.sizcache=y;D.sizset=C
    				}
    				if(typeof x!=="string"){
    					if(D===x){u=true;break}
    				}else{
    					if(j.filter(x,[D]).length>0){u=D;break}
    				}
    			}D=D[v]}z[C]=u}}
    	}
    	
    	var g=/((?:\((?:\([^()]+\)|[^()]+)+\)|\[(?:\[[^[\]]*\]|['"][^'"]*['"]|[^[\]'"]+)+\]|\\.|[^ >+~,(\[\\]+)+|[>+~])(\s*,\s*)?((?:.|\r|\n)*)/g,f=0,i=Object.prototype.toString,h=false,l=true;[0,0].sort(function(){l=false;return 0});var j=function(x,B,v,S){v=v||[];var C=B=B||bp;if(B.nodeType!==1&&B.nodeType!==9){return[]}if(!x||typeof x!=="string"){return v}for(var U=[],E,y,z,I,H=true,u=n(B),M=x;(g.exec(""),E=g.exec(M))!==null;){M=E[3];U.push(E[1]);if(E[2]){I=E[3];break}}if(U.length>1&&p.exec(x)){if(U.length===2&&k.relative[U[0]]){y=a(U[0]+U[1],B)}else{for(y=k.relative[U[0]]?[B]:j(U.shift(),B);U.length;){x=U.shift();if(k.relative[x]){x+=U.shift()}y=a(x,y)}}}else{if(!S&&U.length>1&&B.nodeType===9&&!u&&k.match.ID.test(U[0])&&!k.match.ID.test(U[U.length-1])){E=j.find(U.shift(),B,u);
        B=E.expr?j.filter(E.expr,E.set)[0]:E.set[0]}
         if(B){
        	 E=S?{expr:U.pop(),set:t(S)}:j.find(U.pop(),U.length===1&&(U[0]==="~"||U[0]==="+")&&B.parentNode?B.parentNode:B,u);
        y=E.expr?j.filter(E.expr,E.set):E.set;
        if(U.length>0){z=t(y)}else{H=false}
        for(;U.length;){
        	var D=U.pop();E=D;
        	if(k.relative[D]){
        		E=U.pop()
        	}else{D=""}
        	if(E==null){E=B}
        	k.relative[D](z,E,u)
        }
      }else{z=[]}
  }
   z||(z=y);z||j.error(D||x);
   if(i.call(z)==="[object Array]"){
	   if(H){
		   if(B&&B.nodeType===1){
			   for(x=0;z[x]!=null;x++){
				   if(z[x]&&(z[x]===true||z[x].nodeType===1&&q(B,z[x]))){v.push(y[x])}}
		   }else{
			   for(x=0;z[x]!=null;x++){z[x]&&z[x].nodeType===1&&v.push(y[x])}
		   }
	   }else{v.push.apply(v,z)}
   }else{t(z,v)}
   if(I){j(I,C,v,S);j.uniqueSort(v)}
   return v};
   j.uniqueSort=function(u){
	   if(m){
		   h=l;u.sort(m);
		   if(h){for(var v=1;v<u.length;v++){u[v]===u[v-1]&&u.splice(v--,1)}}
		}
	   return u
    };
    j.matches=function(u,v){return j(u,null,null,v)};
    j.find=function(v,x,y){
    	var z,C;if(!v){return[]}
    	for(var B=0,E=k.order.length;B<E;B++){
    		var D=k.order[B];
    		if(C=k.leftMatch[D].exec(v)){
    			var u=C[1];C.splice(1,1);
    			if(u.substr(u.length-1)!=="\\"){
    				C[1]=(C[1]||"").replace(/\\/g,"");
    				z=k.find[D](C,x,y);
    				if(z!=null){v=v.replace(k.match[D],"");break}
    			}
    		}
    	}
    	z||(z=x.getElementsByTagName("*"));
    	return{set:z,expr:v}
    };
    	j.filter=function(v,x,I,S){
    		for(var C=v,ad=[],E=x,y,M,B=x&&x[0]&&n(x[0]);v&&x.length;){
    			for(var H in k.filter){
    				if((y=k.leftMatch[H].exec(v))!=null&&y[2]){
    					var z=k.filter[H],ac,D;D=y[1];M=false;y.splice(1,1);
    					if(D.substr(D.length-1)!=="\\"){
    						if(E===ad){ad=[]}
    						if(k.preFilter[H]){
    							if(y=k.preFilter[H](y,E,I,ad,S,B)){
    								if(y===true){continue}
    							}else{M=ac=true}
    						}
    						if(y){
    							for(var u=0;(D=E[u])!=null;u++){
    								if(D){ac=z(D,y,u,E);
                                    var U=S^!!ac;
                                    if(I&&ac!=null){
                                    	if(U){
                                    		M=true
                                    		}else{E[u]=false}
                                    }else{
                                    	if(U){ad.push(D);M=true}
                                    }
                                    }
    							}
    						}
    						if(ac!==bs){
    							I||(E=ad);
    							v=v.replace(k.match[H],"");
    							if(!M){return[]}
    							break}
    				 }
    			  }
    		 }
    	  if(v===C){
    		  if(M==null){
    			  j.error(v)
    		}else{break}
    	  }
    	  C=v
    	 }
      return E
      };
     j.error=function(u){
    	 throw"Syntax error, unrecognized expression: "+u
    };
    var k=j.selectors={
    			 order:["ID","NAME","TAG"],
    			 match:{
    		        ID:/#((?:[\w\u00c0-\uFFFF-]|\\.)+)/,
    		        CLASS:/\.((?:[\w\u00c0-\uFFFF-]|\\.)+)/,
    		        NAME:/\[name=['"]*((?:[\w\u00c0-\uFFFF-]|\\.)+)['"]*\]/,ATTR:/\[\s*((?:[\w\u00c0-\uFFFF-]|\\.)+)\s*(?:(\S?=)\s*(['"]*)(.*?)\3|)\s*\]/,
    		        TAG:/^((?:[\w\u00c0-\uFFFF\*-]|\\.)+)/,
    		        CHILD:/:(only|nth|last|first)-child(?:\((even|odd|[\dn+-]*)\))?/,
    		        POS:/:(nth|eq|gt|lt|first|last|even|odd)(?:\((\d*)\))?(?=[^-]|$)/,
    		        PSEUDO:/:((?:[\w\u00c0-\uFFFF-]|\\.)+)(?:\((['"]?)((?:\([^\)]+\)|[^\(\)]*)+)\2\))?/},leftMatch:{},attrMap:{"class":"className","for":"htmlFor"},attrHandle:{href:function(u){return u.getAttribute("href")
                 }
    	 },
    	 relative:{"+":function(x,z){
    	var v=typeof z==="string",u=v&&!/\W/.test(z);v=v&&!u;if(u){z=z.toLowerCase()}
    	u=0;
    	for(var y=x.length,B;u<y;u++){if(B=x[u]){for(;(B=B.previousSibling)&&B.nodeType!==1;){}x[u]=v||B&&B.nodeName.toLowerCase()===z?B||false:B===z}}v&&j.filter(z,x,true)},">":function(x,z){var v=typeof z==="string";if(v&&!/\W/.test(z)){z=z.toLowerCase();for(var u=0,y=x.length;u<y;u++){var B=x[u];if(B){v=B.parentNode;x[u]=v.nodeName.toLowerCase()===z?v:false}}}else{u=0;for(y=x.length;u<y;u++){if(B=x[u]){x[u]=v?B.parentNode:B.parentNode===z}}v&&j.filter(z,x,true)}},"":function(x,z,v){var u=f++,y=e;if(typeof z==="string"&&!/\W/.test(z)){var B=z=z.toLowerCase();y=d}y("parentNode",z,u,x,B,v)},"~":function(x,z,v){var u=f++,y=e;if(typeof z==="string"&&!/\W/.test(z)){var B=z=z.toLowerCase();y=d
}y("previousSibling",z,u,x,B,v)}},
        find:{
	        ID:function(v,u,x){if(typeof u.getElementById!=="undefined"&&!x){return(v=u.getElementById(v[1]))?[v]:[]}},
	         NAME:function(x,y){
	        	   if(typeof y.getElementsByName!=="undefined"){
	        		   var v=[];y=y.getElementsByName(x[1]);
	        		   for(var z=0,u=y.length;z<u;z++){
	        			   y[z].getAttribute("name")===x[1]&&v.push(y[z])}
	        		   return v.length===0?null:v
	        		}
	         },
	         TAG:function(u,v){return v.getElementsByTagName(u[1])}
	    },
	    preFilter:{
	    	CLASS:function(C,x,z,u,B,y){
	    	C=" "+C[1].replace(/\\/g,"")+" ";
	    	if(y){return C}y=0;for(var v;(v=x[y])!=null;y++){
	    		if(v){
	    			if(B^(v.className&&(" "+v.className+" ").replace(/[\t\n]/g," ").indexOf(C)>=0)){z||u.push(v)}else{if(z){x[y]=false}}}
	    		}return false},
	        ID:function(u){return u[1].replace(/\\/g,"")},
	        TAG:function(u){return u[1].toLowerCase()},
	        CHILD:function(u){if(u[1]==="nth"){var v=/(-?)(\d*)n((?:\+|-)?\d*)/.exec(u[2]==="even"&&"2n"||u[2]==="odd"&&"2n+1"||!/\D/.test(u[2])&&"0n+"+u[2]||u[2]);
u[2]=v[1]+(v[2]||1)-0;u[3]=v[3]-0}u[0]=f++;return u},
            ATTR:function(x,z,v,u,y,B){
	                z=x[1].replace(/\\/g,"");
	                if(!B&&k.attrMap[z]){x[1]=k.attrMap[z]}
	                if(x[2]==="~="){x[4]=" "+x[4]+" "}
	                return x},
	         PSEUDO:function(x,y,v,z,u){
	                	if(x[1]==="not"){
	                		if((g.exec(x[3])||"").length>1||/^\w/.test(x[3])){
	                			x[3]=j(x[3],null,null,y)
	                		}else{
	                			x=j.filter(x[3],y,v,true^u);
	                			v||z.push.apply(z,x);
	                			return false}
	                	}else{
	                		if(k.match.POS.test(x[0])||k.match.CHILD.test(x[0])){return true}
	                	}
	              return x},
	            POS:function(u){u.unshift(true);return u}},
	            filters:{
	            	enabled:function(u){return u.disabled===false&&u.type!=="hidden"},
	            	disabled:function(u){return u.disabled===true},
	            	checked:function(u){return u.checked===true},
	            	selected:function(u){return u.selected===true},
	            	parent:function(u){return !!u.firstChild},
	            	empty:function(u){return !u.firstChild},
	            	has:function(v,u,x){return !!j(x[3],v).length},
	            	header:function(u){return/h\d/i.test(u.nodeName)},
	            	text:function(u){return"text"===u.type},
	            	radio:function(u){return"radio"===u.type},
	            	checkbox:function(u){return"checkbox"===u.type},
	            	file:function(u){return"file"===u.type},
	            	password:function(u){return"password"===u.type},
	            	submit:function(u){return"submit"===u.type},
	            	image:function(u){return"image"===u.type},
	            	reset:function(u){return"reset"===u.type},
	            	button:function(u){return"button"===u.type||u.nodeName.toLowerCase()==="button"},
	            	input:function(u){return/input|select|textarea|button/i.test(u.nodeName)}},
	           setFilters:{
	            		first:function(u,v){return v===0},
	                	last:function(x,y,v,u){return y===u.length-1},
	            	    even:function(u,v){return v%2===0},
	            	    odd:function(u,v){return v%2===1},
	            	    lt:function(v,u,x){return u<x[3]-0},
	            	    gt:function(v,u,x){return u>x[3]-0},
	            	    nth:function(v,u,x){return x[3]-0===u},
	            	    eq:function(v,u,x){return x[3]-0===u}},
	           filter:{
	            	    PSEUDO:function(x,z,v,u){
	            	    	var y=z[1],B=k.filters[y];
	            	    	if(B){
	            	    		return B(x,v,z,u)
	            	    	}else{
	            	    		if(y==="contains"){
	            	    			return(x.textContent||x.innerText||b([x])||"").indexOf(z[3])>=0
	            	    		}else{
	            	    			if(y==="not"){
	            	    				z=z[3];v=0;
	            	    				for(u=z.length;v<u;v++){if(z[v]===x){return false}}
	            	    				return true
	            	    			}else{
	            	    				j.error("Syntax error, unrecognized expression: "+y)
	            	    			}
	            	    		}
	            	    	}},
	            	     CHILD:function(C,x){
	            	    		var z=x[1],
	            	    		u=C;
	            	    		switch(z){
	            	    		case"only":
	            	    		case"first":
	            	    			for(;u=u.previousSibling;){
	            	    			   if(u.nodeType===1){return false}
	            	    			}
	            	    		    if(z==="first"){return true}
	            	    		    u=C;
	            	    		case"last":
	            	    			 for(;u=u.nextSibling;){if(u.nodeType===1){return false}}
	            	    			 return true;
	            	    	    case"nth":z=x[2];var B=x[3];
	            	    	    if(z===1&&B===0){return true}
	            	    	    x=x[0];var y=C.parentNode;
	            	    	    if(y&&(y.sizcache!==x||!C.nodeIndex)){
	            	    	    	var v=0;for(u=y.firstChild;u;u=u.nextSibling){if(u.nodeType===1){u.nodeIndex=++v}}y.sizcache=x}
	            	    	    C=C.nodeIndex-B;
	            	    	    return z===0?C===0:C%z===0&&C/z>=0}
	            	    	},
	            	    	ID:function(u,v){return u.nodeType===1&&u.getAttribute("id")===v},
	            	    	TAG:function(u,v){return v==="*"&&u.nodeType===1||u.nodeName.toLowerCase()===v},
	            	    	CLASS:function(u,v){return(" "+(u.className||u.getAttribute("class"))+" ").indexOf(v)>-1},
	            	    	ATTR:function(x,y){
	            	    		var v=y[1];x=k.attrHandle[v]?k.attrHandle[v](x):x[v]!=null?x[v]:x.getAttribute(v);v=x+"";
	            	    		var u=y[2];y=y[4];
	            	    		return x==null?u==="!=":u==="="?v===y:u==="*="?v.indexOf(y)>=0:u==="~="?(" "+v+" ").indexOf(y)>=0:!y?v&&x!==false:u==="!="?v!==y:u==="^="?v.indexOf(y)===0:u==="$="?v.substr(v.length-y.length)===y:u==="|="?v===y||v.substr(0,y.length+1)===y+"-":false},
	            	    	POS:function(x,y,v,z){var u=k.setFilters[y[2]];if(u){return u(x,v,y,z)}}
	            }
	           },
	           p=k.match.POS;
	           for(var o in k.match){
	        	   k.match[o]=new RegExp(k.match[o].source+/(?![^\[]*\])(?![^\(]*\))/.source);
                   k.leftMatch[o]=new RegExp(/(^(?:.|\r|\n)*?)/.source+k.match[o].source.replace(/\\(\d+)/g,function(u,v){return"\\"+(v-0+1)}))}
	           var t=function(u,v){
	        	   u=Array.prototype.slice.call(u,0);
	        	   if(v){v.push.apply(v,u);return v}
	        	   return u
	        	};
	        	try{
	        		Array.prototype.slice.call(bp.documentElement.childNodes,0)
	        	}catch(r){
	        		t=function(x,y){
	        			y=y||[];
	        			if(i.call(x)==="[object Array]"){
	        				Array.prototype.push.apply(y,x)
	        			}else{
	        				if(typeof x.length==="number"){
	        					for(var v=0,u=x.length;v<u;v++){y.push(x[v])}
	        				}else{
	        					for(v=0;x[v];v++){
	        						y.push(x[v])
	        					}
	        				}
	        			}
	        		return y}
	        	}
	        	var m;
	        	   /**/
	        	if(bp.documentElement.compareDocumentPosition){
	        		m=function(u,v){
	        			if(!u.compareDocumentPosition||!v.compareDocumentPosition){
	        				if(u==v){h=true}
	        				return u.compareDocumentPosition?-1:1
	        			}
	        			u=u.compareDocumentPosition(v)&4?-1:u===v?0:1;
	        			if(u===0){
	        				h=true
	        			}
	        			return u
	        		}
	          }else{
	        	  if("sourceIndex" in bp.documentElement){
	        		  m=function(u,v){if(!u.sourceIndex||!v.sourceIndex){if(u==v){h=true
}return u.sourceIndex?-1:1}u=u.sourceIndex-v.sourceIndex;if(u===0){h=true}return u}
	        	 }else{
	        		 if(bp.createRange){
	        			 m=function(x,y){
	        				 if(!x.ownerDocument||!y.ownerDocument){
	        					 if(x==y){h=true}return x.ownerDocument?-1:1}
	        				 var v=x.ownerDocument.createRange(),u=y.ownerDocument.createRange();
	        				 v.setStart(x,0);v.setEnd(x,0);
	        				 u.setStart(y,0);u.setEnd(y,0);
	        				 x=v.compareBoundaryPoints(Range.START_TO_END,u);
	        				 if(x===0){h=true}return x}}}
	        }
	      (function(){
	    	  var v=bp.createElement("div"),
	    	  u="script"+(new Date).getTime();
	    	  v.innerHTML="<a name='"+u+"'/>";
	    	  var x=bp.documentElement;
	    	  x.insertBefore(v,x.firstChild);
	    	  if(bp.getElementById(u)){
	    		  k.find.ID=function(z,B,y){
	    			  if(typeof B.getElementById!=="undefined"&&!y){
	    				  return(B=B.getElementById(z[1]))?B.id===z[1]||typeof B.getAttributeNode!=="undefined"&&B.getAttributeNode("id").nodeValue===z[1]?[B]:bs:[]
                       }
	    	      };
	    	      k.filter.ID=function(z,B){
	    	    	  var y=typeof z.getAttributeNode!=="undefined"&&z.getAttributeNode("id");
	    	    	  return z.nodeType===1&&y&&y.nodeValue===B}
	    	  }
	    	  x.removeChild(v);x=v=null
	    	 })();
	     (function()
	    		 {var u=bp.createElement("div");
	    		 u.appendChild(bp.createComment(""));
	    		 if(u.getElementsByTagName("*").length>0){
	    			 k.find.TAG=function(y,v){
	    				 v=v.getElementsByTagName(y[1]);
	    			if(y[1]==="*"){y=[];for(var x=0;v[x];x++){v[x].nodeType===1&&y.push(v[x])}v=y}return v
	    		    }
	    		 }
	    		 u.innerHTML="<a href='#'></a>";
	    		 if(u.firstChild&&typeof u.firstChild.getAttribute!=="undefined"&&u.firstChild.getAttribute("href")!=="#"){
	    			 k.attrHandle.href=function(v){return v.getAttribute("href",2)}
	    		}
	    		 u=null
	    		})();
	     /**/
	     bp.querySelectorAll&&function(){
	    	 var v=j,u=bp.createElement("div");
	    	 u.innerHTML="<p class='TEST'></p>";
	    	 if(!(u.querySelectorAll&&u.querySelectorAll(".TEST").length===0)){
	    		 j=function(B,y,D,z){
	    			 y=y||bp;
                     if(!z&&y.nodeType===9&&!n(y)){
                    	 try{return t(y.querySelectorAll(B),D)}catch(C){}}
                     return v(B,y,D,z)};
                 for(var x in v){j[x]=v[x]}u=null}}();
                 /**/
        (function(){
        	var u=bp.createElement("div");
        	u.innerHTML="<div class='test e'></div><div class='test'></div>";
        	if(!(!u.getElementsByClassName||u.getElementsByClassName("e").length===0)){
        		u.lastChild.className="e";
        		if(u.getElementsByClassName("e").length!==1){
        			k.order.splice(1,0,"CLASS");
        			k.find.CLASS=function(y,v,x){
        				if(typeof v.getElementsByClassName!=="undefined"&&!x){return v.getElementsByClassName(y[1])}
        			};
        			u=null
        		}
        	}})();
        /**/
        var q=bp.compareDocumentPosition?function(u,v){return !!(u.compareDocumentPosition(v)&16)}:
        	function(u,v){return u!==v&&(u.contains?u.contains(v):true)},
        	n=function(u){return(u=(u?u.ownerDocument||u:0).documentElement)?u.nodeName!=="HTML":false},
         a=function(x,z){var v=[],u="",y;
        for(z=z.nodeType?[z]:z;y=k.match.PSEUDO.exec(x);){u+=y[0];x=x.replace(k.match.PSEUDO,"")}
        x=k.relative[x]?x+"*":x;
        y=0;
        for(var B=z.length;y<B;y++){j(x,z[y],v)}
        return j.filter(u,v)};
        cc.find=j;cc.expr=j.selectors;cc.expr[":"]=cc.expr.filters;
        cc.unique=j.uniqueSort;cc.text=b;cc.isXMLDoc=n;cc.contains=q})();
    		        
    	var bN=/Until$/,T=/^(?:parents|prevUntil|prevAll)/,bm=/,/;cx=Array.prototype.slice;
    	   /**/
    	var bI=function(a,b,d){
    		if(cc.isFunction(b)){
    			return cc.grep(a,function(f,g){return !!b.call(f,g,f)===d})
    		}else{
    			if(b.nodeType){
    				return cc.grep(a,function(f){return f===b===d})
    			}else{
    				if(typeof b==="string"){
    					var e=cc.grep(a,function(f){return f.nodeType===1});
    					if(bq.test(b)){
    						return cc.filter(b,e,!d)
    					}else{b=cc.filter(b,e)}
    				}
    			}
    		}
    		return cc.grep(a,function(f){return cc.inArray(f,b)>=0===d})
    	};
    	   /**/
    	cc.fn.extend({
    			find:function(g){
    		               for(var b=this.pushStack("","find",g),d=0,a=0,f=this.length;a<f;a++){
    		            	   d=b.length;
    		            	   cc.find(g,this[a],b);
    		            	   if(a>0){
    		            		   for(var h=d;h<b.length;h++){
    		            			   for(var e=0;e<d;e++){if(b[e]===b[h]){b.splice(h--,1);break}}
    		            		}
    		            	  }
    		             }return b},
    		   has:function(a){
    		            	 var b=cc(a);
    		            	 return this.filter(
    		            			 function(){
    		            				 for(var d=0,e=b.length;d<e;d++){
    		            					 if(cc.contains(this,b[d])){return true}}
    		            				 }
    		            		)},
    		   not:function(a){
    		            	return this.pushStack(bI(this,a,false),"not",a)},
    		   filter:function(a){return this.pushStack(bI(this,a,true),"filter",a)},
    		   is:function(a){return !!a&&cc.filter(a,this).length>0},
    		   closest:function(f,h){
    			   if(cc.isArray(f)){
    				   var i=[],j=this[0],g,a={},d;
    				   if(j&&f.length){
    					   g=0;
    					   for(var e=f.length;g<e;g++){
    						   d=f[g];a[d]||(a[d]=cc.expr.match.POS.test(d)?cc(d,h||this.context):d)
    						}
    					   for(;j&&j.ownerDocument&&j!==h;){
    						   for(d in a){
    							   g=a[d];
    							   if(g.jquery?g.index(j)>-1:cc(j).is(g)){i.push({selector:d,elem:j});delete a[d]}
    							   }
    						 j=j.parentNode
    						}
    				  }return i
    			 }
    			 var b=cc.expr.match.POS.test(f)?cc(f,h||this.context):null;
    			 return this.map(function(k,l){
    				 for(;l&&l.ownerDocument&&l!==h;){
    					 if(b?b.index(l)>-1:cc(l).is(f)){return l}
    					 l=l.parentNode}return null})},
    		index:function(a){
    						 if(!a||typeof a==="string"){return cc.inArray(this[0],a?cc(a):this.parent().children())}
    		                  return cc.inArray(a.jquery?a[0]:a,this)},
    	     add:function(a,b){
    		                	 a=typeof a==="string"?cc(a,b||this.context):cc.makeArray(a);
    		                	 b=cc.merge(this.get(),a);
    		                	 return this.pushStack(bF(a[0])||bF(b[0])?b:cc.unique(b))},
    		  andSelf:function(){return this.add(this.prevObject)}
    });
    	   /**/
    cc.each({
    	parent:function(a){return(a=a.parentNode)&&a.nodeType!==11?a:null},
    	parents:function(a){return cc.dir(a,"parentNode")},
    	parentsUntil:function(d,a,b){return cc.dir(d,"parentNode",b)},
    	next:function(a){return cc.nth(a,2,"nextSibling")},
    	prev:function(a){return cc.nth(a,2,"previousSibling")},
    	nextAll:function(a){return cc.dir(a,"nextSibling")},
    	prevAll:function(a){return cc.dir(a,"previousSibling")},
    	nextUntil:function(d,a,b){return cc.dir(d,"nextSibling",b)},
    	prevUntil:function(d,a,b){return cc.dir(d,"previousSibling",b)},
    	siblings:function(a){return cc.sibling(a.parentNode.firstChild,a)},
    	children:function(a){return cc.sibling(a.firstChild)},
    	contents:function(a){
    		return cc.nodeName(a,"iframe")?a.contentDocument||a.contentWindow.document:cc.makeArray(a.childNodes)}
    },
   function(a,b){
    	cc.fn[a]=function(e,d){
    		var f=cc.map(this,b,e);bN.test(a)||(d=e);
    		if(d&&typeof d==="string"){f=cc.filter(d,f)}
    		f=this.length>1?cc.unique(f):f;
    		if((this.length>1||bm.test(d))&&T.test(a)){f=f.reverse()}
    		return this.pushStack(f,a,cx.call(arguments).join(","))}
    });
    /**/
    cc.extend({
    	filter:function(d,a,b){if(b){d=":not("+d+")"}return cc.find.matches(d,a)},
    	dir:function(a,b,d){
    		var e=[];
    	   for(a=a[b];a&&a.nodeType!==9&&(d===bs||a.nodeType!==1||!cc(a).is(d));){a.nodeType===1&&e.push(a);a=a[b]}return e},
    	nth:function(a,b,d){
    		   b=b||1;
    	       for(var e=0;a;a=a[d]){if(a.nodeType===1&&++e===b){break}}return a},
    	sibling:function(d,a){for(var b=[];d;d=d.nextSibling){d.nodeType===1&&d!==a&&b.push(d)}return b}
    });
    
    var ca=/ jQuery\d+="(?:\d+|null)"/g,b1=/^\s+/,b0=/(<([\w:]+)[^>]*?)\/>/g,
    O=/^(?:area|br|col|embed|hr|img|input|link|meta|param)$/i,bD=/<([\w:]+)/,ci=/<tbody/i,
    cj=/<|&#?\w+;/,cp=/<script|<object|<embed|<option|<style/i,b8=/checked\s*(?:[^=]|=\s*.checked.)/i,
    bA=function(d,a,b){return O.test(b)?d:a+"></"+b+">"},
    w={option:[1,"<select multiple='multiple'>","</select>"],
    legend:[1,"<fieldset>","</fieldset>"],thead:[1,"<table>","</table>"],
    tr:[2,"<table><tbody>","</tbody></table>"],
    td:[3,"<table><tbody><tr>","</tr></tbody></table>"],
    col:[2,"<table><tbody></tbody><colgroup>","</colgroup></table>"],
    area:[1,"<map>","</map>"],_default:[0,"",""]};
    w.optgroup=w.option;w.tbody=w.tfoot=w.colgroup=w.caption=w.thead; w.th=w.td;
    
    if(!cc.support.htmlSerialize){
    	w._default=[1,"div<div>","</div>"]}
    /**/
    cc.fn.extend({
    	text:function(a){
    	       if(cc.isFunction(a)){return this.each(function(b){var d=cc(this);d.text(a.call(this,b,d.text()))})}
    	       if(typeof a!=="object"&&a!==bs){
    	    	   return this.empty().append((this[0]&&this[0].ownerDocument||bp).createTextNode(a))
    	    	}
    	       return cc.text(this)},
    	  wrapAll:function(a){
    	    	   if(cc.isFunction(a)){return this.each(function(d){cc(this).wrapAll(a.call(this,d))})}
    	    	   if(this[0]){
    	    		   var b=cc(a,this[0].ownerDocument).eq(0).clone(true);
    	    	       this[0].parentNode&&b.insertBefore(this[0]);
    	    	       b.map(function(){
    	    		     for(var d=this;d.firstChild&&d.firstChild.nodeType===1;){d=d.firstChild}return d}
    	    	      ).append(this)
    	    	   }
    	    	   return this},
    	  wrapInner:function(a){
    	    		   if(cc.isFunction(a)){
    	    			   return this.each(
    	    					   function(b){cc(this).wrapInner(a.call(this,b))}
    	    				)}
    	    		   return this.each(
    	    				   function(){var b=cc(this),d=b.contents();d.length?d.wrapAll(a):b.append(a)})},
    	  wrap:function(a){
    	    			 return this.each(function(){cc(this).wrapAll(a)})},
    	  unwrap:function(){
    	    				 return this.parent().each(function(){
    	    					 cc.nodeName(this,"body")||cc(this).replaceWith(this.childNodes)}
    	    				 ).end()},
    	  append:function(){return this.domManip(arguments,true,function(a){this.nodeType===1&&this.appendChild(a)})},
    	  prepend:function(){
    		  return this.domManip(arguments,
    				 true,
    				 function(a){
    			          this.nodeType===1&&this.insertBefore(a,this.firstChild)})},
    	  before:function(){
    			        	  if(this[0]&&this[0].parentNode){
    			        		  return this.domManip(arguments,false,function(b){this.parentNode.insertBefore(b,this)})
    			        	  }else{
    			        		  if(arguments.length){
    			        			  var a=cc(arguments[0]);
    			        			  a.push.apply(a,this.toArray());
    			        			  return this.pushStack(a,"before",arguments)}}},
    	after:function(){
    			    if(this[0]&&this[0].parentNode){
    			    	return this.domManip(arguments,false,function(b){this.parentNode.insertBefore(b,this.nextSibling)})
                    }else{
                    	if(arguments.length){
                    		var a=this.pushStack(this,"after",arguments);
                    		a.push.apply(a,cc(arguments[0]).toArray());return a}}
    			    },
      
       remove:function(a,b){
                    			for(var d=0,e;(e=this[d])!=null;d++){
                    				if(!a||cc.filter(a,[e]).length){
                    					if(!b&&e.nodeType===1){
                    						cc.cleanData(e.getElementsByTagName("*"));cc.cleanData([e])
                    					}
                    					e.parentNode&&e.parentNode.removeChild(e)
                    				}
                    			}
                    			return this
                    	    },
        empty:function(){
                    		for(var a=0,b;(b=this[a])!=null;a++){
                    			for(b.nodeType===1&&cc.cleanData(b.getElementsByTagName("*"));b.firstChild;){
                    				b.removeChild(b.firstChild)}
                    			}
                    			return this
                    	},
       clone:function(a){
                    					var b=this.map(function(){
                    						if(!cc.support.noCloneEvent&&!cc.isXMLDoc(this)){
                    							var d=this.outerHTML,e=this.ownerDocument;
                    							if(!d){
                    								d=e.createElement("div");
                    								d.appendChild(this.cloneNode(true));
                    								d=d.innerHTML
                    							}
                    							return cc.clean([d.replace(ca,"").replace(/=([^="'>\s]+\/)>/g,'="$1">').replace(b1,"")],e)[0]
                                            }else{
                                            	return this.cloneNode(true)}
                    					   });
                    				if(a===true){
                    					bO(this,b);
                    					bO(this.find("*"),b.find("*"))}
                    				return b
           },
         html:function(a){
                    				if(a===bs){
                    					return this[0]&&this[0].nodeType===1?this[0].innerHTML.replace(ca,""):null
                    				}else{
                    					if(typeof a==="string"&&!cp.test(a)&&(cc.support.leadingWhitespace||!b1.test(a))&&!w[(bD.exec(a)||["",""])[1].toLowerCase()]){
                    						a=a.replace(b0,bA);
                    					    try{
                    					    	for(var b=0,d=this.length;b<d;b++){
                    					    		if(this[b].nodeType===1){
                    					    			cc.cleanData(this[b].getElementsByTagName("*"));this[b].innerHTML=a
                    					    		}
                    					    		}
                    					    }catch(e){
                    					    	this.empty().append(a)
                    					    }
                    					  }else{
                    						  cc.isFunction(a)?this.each(function(f){
                    							  var h=cc(this),g=h.html();
                    							  h.empty().append(function(){return a.call(this,f,g)})})
                    							  :this.empty().append(a)}}return this
            },
            replaceWith:function(a){
                    				if(this[0]&&this[0].parentNode){
                    					if(cc.isFunction(a)){
                    						return this.each(function(d){
                    							var e=cc(this),b=e.html();
                                                e.replaceWith(a.call(this,d,b))})}
                    					if(typeof a!=="string"){a=cc(a).detach()}
                    					return this.each(function(){
                    						var b=this.nextSibling,d=this.parentNode;
                    						cc(this).remove();
                    						b?cc(b).before(a):cc(d).append(a)})
                    				}else{
                    					return this.pushStack(cc(cc.isFunction(a)?a():a),"replaceWith",a)}
            },
          detach:function(a){return this.remove(a,true)},
            domManip:function(d,f,h){
                    		 function k(m){
                    			 return cc.nodeName(m,"table")?m.getElementsByTagName("tbody")[0]||m.appendChild(m.ownerDocument.createElement("tbody")):m}
                    		 var j,l,i=d[0],e=[],b;
                    		 if(!cc.support.checkClone&&arguments.length===3&&typeof i==="string"&&b8.test(i)){
                    			 return this.each(
                    					 function(){cc(this).domManip(d,f,h,true)})
                    		 }
                    		 if(cc.isFunction(i)){
                    			 return this.each(
                    					 function(m){
                    						 var n=cc(this);
                    						 d[0]=i.call(this,m,f?n.html():bs);
                    						 n.domManip(d,f,h)})
                    		}
                    		if(this[0]){
                    			j=i&&i.parentNode;
                    			j=cc.support.parentNode&&j&&j.nodeType===11&&j.childNodes.length===this.length?{fragment:j}:aa(d,this,e);
                                b=j.fragment;
                                if(l=b.childNodes.length===1?(b=b.firstChild):b.firstChild){
                                	f=f&&cc.nodeName(l,"tr");
                                    for(var a=0,g=this.length;a<g;a++){
                                	 h.call(f?k(this[a],l):this[a],a>0||j.cacheable||this.length>1?b.cloneNode(true):b)
                                   }
                                }
                                e.length&&cc.each(e,ct)
                             }
                    		return this
        }
                    	 });
    cc.fragments={};
    /**/
    cc.each({
    	appendTo:"append",
    	prependTo:"prepend",
    	insertBefore:"before",
    	insertAfter:"after",
    	replaceAll:"replaceWith"},
    	function(a,b){
    		cc.fn[a]=function(h){
    			var e=[];h=cc(h);
    			var d=this.length===1&&this[0].parentNode;
    			if(d&&d.nodeType===11&&d.childNodes.length===1&&h.length===1){
    				h[b](this[0]);return this
    			}else{
    				d=0;
    				for(var g=h.length;d<g;d++){
    					var f=(d>0?this.clone(true):this).get();cc.fn[b].apply(cc(h[d]),f);e=e.concat(f)
    				}
    				return this.pushStack(e,a,h.selector)}}
    });
    /**/
    cc.extend({
    	clean:function(d,f,h,k){f=f||bp;
    	         if(typeof f.createElement==="undefined"){f=f.ownerDocument||f[0]&&f[0].ownerDocument||bp}
    	         for(var j=[],l=0,i;(i=d[l])!=null;l++){
    	        	 if(typeof i==="number"){i+=""}
    	        	 if(i){
    	        		 if(typeof i==="string"&&!cj.test(i)){
    	        			 i=f.createTextNode(i)
    	        		  }else{
    	        			  if(typeof i==="string"){
    	        				  i=i.replace(b0,bA);
    	        				  var e=(bD.exec(i)||["",""])[1].toLowerCase(),b=w[e]||w._default,a=b[0],g=f.createElement("div");
    	        				  for(g.innerHTML=b[1]+i+b[2];a--;){g=g.lastChild}
    	        				  if(!cc.support.tbody){
    	        					  a=ci.test(i);
    	        					  e=e==="table"&&!a?g.firstChild&&g.firstChild.childNodes:b[1]==="<table>"&&!a?g.childNodes:[];
    	        					  for(b=e.length-1;b>=0;--b){
    	        						  cc.nodeName(e[b],"tbody")&&!e[b].childNodes.length&&e[b].parentNode.removeChild(e[b])}
    	        				  }
    	        				  !cc.support.leadingWhitespace&&b1.test(i)&&g.insertBefore(f.createTextNode(b1.exec(i)[0]),g.firstChild);
    	        				  i=g.childNodes
    	        				 }
    	        		}
    	        		if(i.nodeType){
    	        			j.push(i)
    	        		}else{
    	        			j=cc.merge(j,i)
    	        		}
    	        		}
    	        	 }
    	         if(h){for(l=0;j[l];l++){
    	        	 if(k&&cc.nodeName(j[l],"script")&&(!j[l].type||j[l].type.toLowerCase()==="text/javascript")){
    	        		 k.push(j[l].parentNode?j[l].parentNode.removeChild(j[l]):j[l])
                     }else{
                    	 j[l].nodeType===1&&j.splice.apply(j,[l+1,0].concat(cc.makeArray(j[l].getElementsByTagName("script"))));h.appendChild(j[l])
                    }
    	        }
    	        }return j},
    	 cleanData:function(f){
    	        	for(var h,i,j=cc.cache,g=cc.event.special,a=cc.support.deleteExpando,d=0,e;(e=f[d])!=null;d++){
    	        		if(i=e[cc.expando]){
    	        			h=j[i];
    	        			if(h.events){
    	        				for(var b in h.events){
    	        					g[b]?cc.event.remove(e,b):J(e,b,h.handle)
    	        				}
    	        			}
    	        			if(a){
    	        				delete e[cc.expando]
    	        			}else{
    	        				e.removeAttribute&&e.removeAttribute(cc.expando)
    	        			}
    	        			delete j[i]
    	        		}
    	         }
    	      }
    	 });
    /**/
      var bL=/z-?index|font-?weight|opacity|zoom|line-?height/i,Z=/alpha\([^)]*\)/,
      bu=/opacity=([^)]*)/,
      N=/float/i,cg=/-([a-z])/ig,
      bT=/([A-Z])/g,ck=/^-?\d+(?:px)?$/i,
      F=/^-?\d/,
      ce={position:"absolute",visibility:"hidden",display:"block"},L=["Left","Right"],
      bG=["Top","Bottom"],bP=bp.defaultView&&bp.defaultView.getComputedStyle,
      b7=cc.support.cssFloat?"cssFloat":"styleFloat",
    		   /**/
      ch=function(a,b){
             return b.toUpperCase()
             
         };           
/**/
cc.fn.css=function(a,b){
	                   return b3(this,a,b,true,function(e,d,f){
	                	   if(f===bs){return cc.curCSS(e,d)}
	                	   if(typeof f==="number"&&!bL.test(d)){f+="px"}
	                	   cc.style(e,d,f)})
	         };
	                	   /**/
	                	   cc.extend({
	                		   style:function(e,b,a){
	                		   if(!e||e.nodeType===3||e.nodeType===8){return bs}
	                		   if((b==="width"||b==="height")&&parseFloat(a)<0){
	                			   a=bs}var f=e.style||e,d=a!==bs;
	                			   if(!cc.support.opacity&&b==="opacity"){
	                				   if(d){
	                					   f.zoom=1;
	                					   b=parseInt(a,10)+""==="NaN"?"":"alpha(opacity="+a*100+")";
	                					   e=f.filter||cc.curCSS(e,"filter")||"";
	                					   f.filter=Z.test(e)?e.replace(Z,b):b
	                				   }
	                				   return f.filter&&f.filter.indexOf("opacity=")>=0?parseFloat(bu.exec(f.filter)[1])/100+"":""
	                			    }
	                			   if(N.test(b)){b=b7}
	                			   b=b.replace(cg,ch);
	                			   if(d){f[b]=a}
	                			   return f[b]},
	                			 css:function(g,b,d,a){
	                				   if(b==="width"||b==="height"){
	                					   var f,h=b==="width"?L:bG;
	                					   function e(){
	                						   f=b==="width"?g.offsetWidth:g.offsetHeight;
	                						   a!=="border"&&cc.each(h,function(){
	                							  a||(f-=parseFloat(cc.curCSS(g,"padding"+this,true))||0);
                                                  if(a==="margin"){
                                                	  f+=parseFloat(cc.curCSS(g,"margin"+this,true))||0
                                                   }else{
                                                	   f-=parseFloat(cc.curCSS(g,"border"+this+"Width",true))||0}})
                                           }
	                					   g.offsetWidth!==0?e():cc.swap(g,ce,e);
	                					   return Math.max(0,Math.round(f))
	                					 }
	                				    return cc.curCSS(g,b,d)},
	                				    curCSS:function(f,b,e){
	                				    	var d,a=f.style;
	                				    	if(!cc.support.opacity&&b==="opacity"&&f.currentStyle){
	                				    		d=bu.test(f.currentStyle.filter||"")?parseFloat(RegExp.$1)/100+"":"";return d===""?"1":d
	                				    	}
	                				    	if(N.test(b)){b=b7}
	                				    	if(!e&&a&&a[b]){
	                				    		d=a[b]
	                				    	 }else{
	                				    		 if(bP){
	                				    			 if(N.test(b)){b="float"}
	                				    			 b=b.replace(bT,"-$1").toLowerCase();
	                				    			 a=f.ownerDocument.defaultView;
	                				    			 if(!a){return null}
	                				    			 if(f=a.getComputedStyle(f,null)){
	                				    				 d=f.getPropertyValue(b)
	                				    			 }
	                				    			 if(b==="opacity"&&d===""){d="1"}
	                				    	      }else{
	                				    	    	  if(f.currentStyle){
	                				    	    		  e=b.replace(cg,ch);
	                				    	    		  d=f.currentStyle[b]||f.currentStyle[e];
	                				    	    		  if(!ck.test(d)&&F.test(d)){
	                				    	    			  b=a.left;
	                				    	    			  var g=f.runtimeStyle.left;
                                                              f.runtimeStyle.left=f.currentStyle.left;
                                                              a.left=e==="fontSize"?"1em":d||0;d=a.pixelLeft+"px";
                                                              a.left=b;f.runtimeStyle.left=g
                                                           }
	                				    	    	  }
	                				    	    	}
	                				    		}return d},
	                				     swap:function(e,b,a){
	                				    			var f={};
	                				    			for(var d in b){
	                				    				f[d]=e.style[d];
	                				    				e.style[d]=b[d]
	                				    			}
	                				    			a.call(e);
	                				    			for(d in b){e.style[d]=f[d]}
	                				    		}});
	                	   /**/
	                       if(cc.expr&&cc.expr.filters){
	                    	   cc.expr.filters.hidden=function(a){
	                    		   var b=a.offsetWidth,d=a.offsetHeight,
	                    		   e=a.nodeName.toLowerCase()==="tr";
	                    		   return b===0&&d===0&&!e?true:b>0&&d>0&&!e?false:cc.curCSS(a,"display")==="none"};
	                    		   cc.expr.filters.visible=function(a){
	                    			   return !cc.expr.filters.hidden(a)
	                    			}
	                       }
	                       /**/
	                       var ab=K(),bE=/<script(.|\s)*?\/script>/gi,
	                    		b9=/select|textarea/i,
	                    		c=/color|date|datetime|email|hidden|month|number|password|range|search|tel|text|time|url|week/i,R=/=\?(&|$)/,bK=/\?/,bB=/(\?|&)_=.*?(&|$)/,by=/^(\w+:)?\/\/([^\/?#]+)/,
	                    		Y=/%20/g,bt=cc.fn.load;
	                       /**/
	                       cc.fn.extend({
	                    	   load:function(f,b,e){
	                    	   if(typeof f!=="string"){
	                    		   return bt.call(this,f)
	                    	   }else{
	                    		   if(!this.length){return this}
	                    	   }
	                    	   var d=f.indexOf(" ");
	                    	   if(d>=0){var a=f.slice(d,f.length);f=f.slice(0,d)}
	                    	   d="GET";
	                    	   if(b){
	                    		   if(cc.isFunction(b)){
	                    			   e=b;b=null
	                    			}else{
	                    				if(typeof b==="object"){
	                    					b=cc.param(b,cc.ajaxSettings.traditional);d="POST"}
	                    			}
	                    	   }
	                    	   var g=this;
	                    	   cc.ajax({
	                    		   url:f,type:d,dataType:"html",data:b,
	                    		   complete:function(i,h){
	                    		      if(h==="success"||h==="notmodified"){
	                    		    	  g.html(a?cc("<div />").append(i.responseText.replace(bE,"")).find(a):i.responseText)
	                    		      }
	                    		      e&&g.each(e,[i.responseText,h,i])
	                    		    }
	                    	  });
	                    	   return this},
	                    	   serialize:function(){
	                    		   return cc.param(this.serializeArray())},
	                    	   serializeArray:function(){
	                    			   return this.map(function(){
	                    				   return this.elements?cc.makeArray(this.elements):this}
	                    			   ).filter(
	                    					   function(){
	                    						   return this.name&&!this.disabled&&(this.checked||b9.test(this.nodeName)||c.test(this.type))}
	                    			).map(
	                    				function(a,b){
	                    				    a=cc(this).val();
	                    				     return a==null?null:cc.isArray(a)?cc.map(a,
	                    				    		 function(d){
	                    				    	        return{name:b.name,value:d}
	                    				    	     }):{name:b.name,value:a}}).get()
	                    	   }
	                      });
	                       /**/
	                   cc.each("ajaxStart ajaxStop ajaxComplete ajaxError ajaxSuccess ajaxSend".split(" "),
	                		   function(a,b){
	                	             cc.fn[b]=function(d){return this.bind(b,d)}
	                	             });
	                   /**/
	                  cc.extend({
	                	  get:function(a,b,d,e){
	                	       if(cc.isFunction(b)){e=e||d;d=b;b=null}
	                	       return cc.ajax({type:"GET",url:a,data:b,success:d,dataType:e})},
	                	   getScript:function(a,b){return cc.get(a,null,b,"script")},
	                	   getJSON:function(d,a,b){return cc.get(d,a,b,"json")},
	                	   post:function(a,b,d,e){
	                		   if(cc.isFunction(b)){
	                			   e=e||d;d=b;b={}
	                		   }
	                		   return cc.ajax({type:"POST",url:a,data:b,success:d,dataType:e})},
	                		ajaxSetup:function(a){cc.extend(cc.ajaxSettings,a)},
	                		ajaxSettings:{
	                			url:location.href,
	                			global:true,
	                			type:"GET",
	                			contentType:"application/x-www-form-urlencoded",
	                			processData:true,
	                			async:true,
	                			xhr:bC.XMLHttpRequest&&(bC.location.protocol!=="file:"||!bC.ActiveXObject)?
	                					function(){return new bC.XMLHttpRequest}:
	                				    function(){try{return new bC.ActiveXObject("Microsoft.XMLHTTP")}catch(a){}},
	                		     accepts:{xml:"application/xml, text/xml",
	                				      html:"text/html",
	                				      script:"text/javascript, application/javascript",
	                				      json:"application/json, text/javascript",
	                				      text:"text/plain",
	                				      _default:"*/*"}},
	                		lastModified:{},
	                		etag:{},
	                		ajax:function(v){
	                			function x(){a.success&&a.success.call(h,l,f,r);a.global&&b("ajaxSuccess",[r,a])}
	                			function y(){
	                				a.complete&&a.complete.call(h,r,f);
	                			    a.global&&b("ajaxComplete",[r,a]);
	                			    a.global&&!--cc.active&&cc.event.trigger("ajaxStop")
	                			 }
	                			function b(z,B){(a.context?cc(a.context):cc.event).trigger(z,B)}
	                			var a=cc.extend(true,{},cc.ajaxSettings,v),g,f,l,h=v&&v.context||a,k=a.type.toUpperCase();
	                			if(a.data&&a.processData&&typeof a.data!=="string"){
	                				a.data=cc.param(a.data,a.traditional)
	                			}
	                			if(a.dataType==="jsonp"){
	                				if(k==="GET"){
	                					R.test(a.url)||(a.url+=(bK.test(a.url)?"&":"?")+(a.jsonp||"callback")+"=?")
	                				}else{
	                					if(!a.data||!R.test(a.data)){
	                						a.data=(a.data?a.data+"&":"")+(a.jsonp||"callback")+"=?"
	                					}
	                				}
	                				a.dataType="json"
	                			}
	                			if(a.dataType==="json"&&(a.data&&R.test(a.data)||R.test(a.url))){
	                				g=a.jsonpCallback||"jsonp"+ab++;
	                				if(a.data){a.data=(a.data+"").replace(R,"="+g+"$1")}
	                				a.url=a.url.replace(R,"="+g+"$1");
	                				a.dataType="script";
	                				bC[g]=bC[g]||function(z){
	                					l=z;x();y();bC[g]=bs;
	                					try{
	                						delete bC[g]
	                					}catch(B){}
	                					p&&p.removeChild(q)
	                				}
	                			}
	                			if(a.dataType==="script"&&a.cache===null){a.cache=false}
	                			if(a.cache===false&&k==="GET"){
	                				var o=K(),n=a.url.replace(bB,"$1_="+o+"$2");
	                				a.url=n+(n===a.url?(bK.test(a.url)?"&":"?")+"_="+o:"")
	                			}
	                			if(a.data&&k==="GET"){a.url+=(bK.test(a.url)?"&":"?")+a.data}
	                			a.global&&!cc.active++&&cc.event.trigger("ajaxStart");
	                			o=(o=by.exec(a.url))&&(o[1]&&o[1]!==location.protocol||o[2]!==location.host);
	                			if(a.dataType==="script"&&k==="GET"&&o){
	                				var p=bp.getElementsByTagName("head")[0]||bp.documentElement,
	                				q=bp.createElement("script");
                                    q.src=a.url;
                                    if(a.scriptCharset){q.charset=a.scriptCharset}
                                    if(!g){
                                    	var m=false;
                                    	q.onload=q.onreadystatechange=function(){
                                    		if(!m&&(!this.readyState||this.readyState==="loaded"||this.readyState==="complete")){
                                    			m=true;x();y();q.onload=q.onreadystatechange=null;p&&q.parentNode&&p.removeChild(q)
                                    		}
                                    	}
                                    }
                                    p.insertBefore(q,p.firstChild);
                                    return bs
                               }
	                			var u=false,r=a.xhr();
	                			if(r){
	                				a.username?r.open(k,a.url,a.async,a.username,a.password):r.open(k,a.url,a.async);
	                				try{
	                					if(a.data||v&&v.contentType){
	                						r.setRequestHeader("Content-Type",a.contentType)
	                					}
	                				    if(a.ifModified){
	                					cc.lastModified[a.url]&&r.setRequestHeader("If-Modified-Since",cc.lastModified[a.url]);
	                					cc.etag[a.url]&&r.setRequestHeader("If-None-Match",cc.etag[a.url])
	                					}
	                				    o||r.setRequestHeader("X-Requested-With","XMLHttpRequest");
	                				    r.setRequestHeader("Accept",a.dataType&&a.accepts[a.dataType]?a.accepts[a.dataType]+", */*":a.accepts._default)
	                				}catch(t){}
	                			    if(a.beforeSend&&a.beforeSend.call(h,r,a)===false){
	                			    	a.global&&!--cc.active&&cc.event.trigger("ajaxStop");
	                			    	r.abort();return false
	                			    }
	                			    a.global&&b("ajaxSend",[r,a]);
	                			    var d=r.onreadystatechange=function(z){
	                			    	if(!r||r.readyState===0||z==="abort"){
	                			    		u||y();
	                			    		u=true;
	                			    		if(r){r.onreadystatechange=cc.noop}
	                			    	}else{
	                			    		if(!u&&r&&(r.readyState===4||z==="timeout")){
	                			    			u=true;
	                			    			r.onreadystatechange=cc.noop;
	                			    			f=z==="timeout"?
	                			    					"timeout":!cc.httpSuccess(r)?
	                			    					"error":a.ifModified&&cc.httpNotModified(r,a.url)?
	                			    					"notmodified":"success";
	                			    			var C;if(f==="success"){
	                			    				try{l=cc.httpData(r,a.dataType,a)}catch(B){f="parsererror";C=B}
	                			    			}
	                			    			if(f==="success"||f==="notmodified"){
	                			    				g||x()
	                			    			}else{
	                			    				cc.handleError(a,r,f,C)
	                			    			}
	                			    			y();
	                			    			z==="timeout"&&r.abort();
	                			    			if(a.async){r=null}
	                			    		}
	                			    	 }
	                			    	};
	                			    try{
	                			    	var e=r.abort;
	                			    	r.abort=function(){r&&e.call(r);d("abort")}
	                			    }catch(i){}
	                			      a.async&&a.timeout>0&&setTimeout(function(){r&&!u&&d("timeout")},a.timeout);
	                			      try{
	                			    	  r.send(k==="POST"||k==="PUT"||k==="DELETE"?a.data:null)
	                			       }catch(j){
	                			    	   cc.handleError(a,r,null,j);y()
	                			       }
	                			       a.async||d();return r
	                			    }},
	                handleError:function(a,b,d,e){
	                			    	if(a.error){a.error.call(a.context||a,b,d,e)}
	                			    	if(a.global){(a.context?cc(a.context):cc.event).trigger("ajaxError",[b,a,e])}
	                			    },
	                active:0,
	                httpSuccess:function(a){
	                			    	try{
	                			    		return !a.status&&location.protocol==="file:"
	                			    			    ||a.status>=200&&a.status<300
	                			    			    ||a.status===304||a.status===1223||a.status===0
	                			    		}catch(b){}
	                			    	return false},
	                httpNotModified:function(a,b){
	                			    		var d=a.getResponseHeader("Last-Modified"),
	                			    		e=a.getResponseHeader("Etag");
	                			    		if(d){cc.lastModified[b]=d}
	                			    		if(e){cc.etag[b]=e}
	                			    		return a.status===304||a.status===0},
	                httpData:function(e,b,a){
	                			    		var f=e.getResponseHeader("content-type")||"",
	                			    		d=b==="xml"||!b&&f.indexOf("xml")>=0;
	                			    		e=d?e.responseXML:e.responseText;
	                			    		d&&e.documentElement.nodeName==="parsererror"&&cc.error("parsererror");
	                			    		if(a&&a.dataFilter){e=a.dataFilter(e,b)}
	                			    		if(typeof e==="string"){
	                			    			if(b==="json"||!b&&f.indexOf("json")>=0){
	                			    				e=cc.parseJSON(e)
	                			    			}else{
	                			    				if(b==="script"||!b&&f.indexOf("javascript")>=0){
	                			    				cc.globalEval(e)}
	                			    		    }
	                			    		}
	                			    		return e},
	                param:function(f,b){
	                			    	function e(i,h){
	                			    		if(cc.isArray(h)){
	                			    			cc.each(h,function(k,j){b||/\[\]$/.test(i)?d(i,j):e(i+"["+(typeof j==="object"||cc.isArray(j)?k:"")+"]",j)})
	                			    		}else{
	                			    			!b&&h!=null&&typeof h==="object"?cc.each(h,function(k,j){e(i+"["+k+"]",j)}):d(i,h)
	                			    	    }}
	                			    	function d(i,h){
	                			    		h=cc.isFunction(h)?
	                			    				h():h;a[a.length]=encodeURIComponent(i)+"="+encodeURIComponent(h)
	                			    	}
	                			    	var a=[];
	                			    	if(b===bs){b=cc.ajaxSettings.traditional}
	                			    	if(cc.isArray(f)||f.jquery){
	                			    		cc.each(f,function(){d(this.name,this.value)})
	                			    	}else{
	                			    		for(var g in f){e(g,f[g])}
	                			    	}
	                			    	return a.join("&").replace(Y,"+")
	                			  }});
	                var b={},
	                bY=/toggle|show|hide/,
	                bl=/^([+-]=)?([\d+-.]+)(.*)$/,
	                b2,cw=[["height","marginTop","marginBottom","paddingTop","paddingBottom"],
	                      ["width","marginLeft","marginRight","paddingLeft","paddingRight"],
	                      ["opacity"]];
	                /**/
	                cc.fn.extend({
	                	show:function(e,b){
	                	if(e||e===0){
	                		return this.animate(P("show",3),e,b)
	                	}else{
	                		e=0;
	                		for(b=this.length;e<b;e++){
	                			var a=cc.data(this[e],
	                					"olddisplay");this[e].style.display=a||"";
	                					if(cc.css(this[e],"display")==="none"){
	                						a=this[e].nodeName;
	                						var f;
	                						if(bR[a]){
	                							f=bR[a]
	                						}else{
	                							var d=cc("<"+a+" />").appendTo("body");
	                							f=d.css("display");
	                							if(f==="none"){f="block"}
	                							d.remove();
	                							bR[a]=f
	                						}
	                						cc.data(this[e],"olddisplay",f)
	                					}
	                		}
	                		e=0;
	                		for(b=this.length;e<b;e++){
	                			this[e].style.display=cc.data(this[e],"olddisplay")||""
	                		}
	                		return this
	                	}},
	                hide:function(d,a){
	                		if(d||d===0){
	                			return this.animate(P("hide",3),d,a)
	                		}else{
	                			d=0;
	                			for(a=this.length;d<a;d++){
	                				var b=cc.data(this[d],"olddisplay");
	                				!b&&b!=="none"&&cc.data(this[d],"" +
	                						"olddisplay",cc.css(this[d],"display"))
	                			}
	                			d=0;
	                			for(a=this.length;d<a;d++){this[d].style.display="none"}
	                			return this
	                		}
	                	},
	                _toggle:cc.fn.toggle,
	                toggle:function(d,a){
	                		var b=typeof d==="boolean";
	                		if(cc.isFunction(d)&&cc.isFunction(a)){
	                			this._toggle.apply(this,arguments)
	                		}else{
	                			d==null||b?this.each(function(){
	                				var e=b?d:cc(this).is(":hidden");
	                				cc(this)[e?"show":"hide"]()
	                				}):this.animate(P("toggle",3),d,a)}return this},
	                fadeTo:function(d,a,b){
	                		return this.filter(":hidden").css("opacity",0).show().end().animate({opacity:a},d,b)},
	                animate:function(e,b,a,f){
	                			var d=cc.speed(b,a,f);
	                			if(cc.isEmptyObject(e)){
	                				return this.each(d.complete)
	                			}
	                			return this[d.queue===false?"each":"queue"](
	                					function(){
	                						var h=cc.extend({},d),g,k=this.nodeType===1&&cc(this).is(":hidden"),
	                						i=this;
	                						for(g in e){
	                							var j=g.replace(cg,ch);
	                							if(g!==j){
	                								e[j]=e[g];
	                							  delete e[g];g=j
	                							 }
	                							if(e[g]==="hide"&&k||e[g]==="show"&&!k){return h.complete.call(this)}
	                							if((g==="height"||g==="width")&&this.style){
	                								h.display=cc.css(this,"display");
	                							    h.overflow=this.style.overflow
	                							}
	                							if(cc.isArray(e[g])){
	                								(h.specialEasing=h.specialEasing||{})[g]=e[g][1];e[g]=e[g][0]}
	                						}
	                						if(h.overflow!=null){this.style.overflow="hidden"}
	                						h.curAnim=cc.extend({},e);
	                						cc.each(e,function(n,o){
	                							var q=new cc.fx(i,h,n);
	                							if(bY.test(o)){
	                								q[o==="toggle"?k?"show":"hide":o](e)
	                							}else{
	                								var l=bl.exec(o),p=q.cur(true)||0;
	                								if(l){
	                									o=parseFloat(l[2]);
	                								    var m=l[3]||"px";
	                								    if(m!=="px"){
	                								    	i.style[n]=(o||1)+m;
	                								    	p=(o||1)/q.cur(true)*p;
	                								    	i.style[n]=p+m
	                								    }
	                								    if(l[1]){
	                								    	o=(l[1]==="-="?-1:1)*o+p
	                								    }
	                								    q.custom(p,o,m)
	                								 }else{
	                									 q.custom(p,o,"")
	                								 }
	                							}});
	                						return true}
	                					)},
	                			stop:function(d,a){
	                						var b=cc.timers;d&&this.queue([]);
	                						this.each(function(){
	                							for(var e=b.length-1;e>=0;e--){
	                								if(b[e].elem===this){a&&b[e](true);b.splice(e,1)}
	                							}
	                						});
	                						a||this.dequeue();
	                						return this}});
	                /**/
	        cc.each({
	        	slideDown:P("show",1),
	        	slideUp:P("hide",1),
	        	slideToggle:P("toggle",1),
	        	fadeIn:{opacity:"show"},
	        	fadeOut:{opacity:"hide"}},
	        	function(a,b){
	        		cc.fn[a]=function(d,e){
	        			return this.animate(b,d,e)
	        		}
	        });
	        /**/
	       cc.extend({
	    	   speed:function(a,b,d){
	    	   var e=a&&typeof a==="object"?
	    			   a:{complete:d||!d&&b||cc.isFunction(a)&&a,duration:a,easing:d&&b||b&&!cc.isFunction(b)&&b};
	    			   e.duration=cc.fx.off?0:typeof e.duration==="number"?
	    			   e.duration:cc.fx.speeds[e.duration]||cc.fx.speeds._default;e.old=e.complete;
	    			   e.complete=function(){e.queue!==false&&cc(this).dequeue();
	    			   cc.isFunction(e.old)&&e.old.call(this)};return e},
	    	  easing:{
	    				linear:function(a,b,d,e){return d+e*a},
	    				swing:function(a,b,d,e){return(-Math.cos(a*Math.PI)/2+0.5)*e+d}},timers:[],
                        fx:function(d,a,b){this.options=a;this.elem=d;this.prop=b;if(!a.orig){a.orig={}}}
	    	});
	       /**/
	       cc.fx.prototype={
	    	   update:function(){
	    	   this.options.step&&this.options.step.call(this.elem,this.now,this);
	    	   (cc.fx.step[this.prop]||cc.fx.step._default)(this);
	    	   if((this.prop==="height"||this.prop==="width")&&this.elem.style){this.elem.style.display="block"}
	    	   },
	    	   cur:function(a){
	    		   if(this.elem[this.prop]!=null&&(!this.elem.style||this.elem.style[this.prop]==null)){
	    			   return this.elem[this.prop]
	    		   }
	    	      return(a=parseFloat(cc.css(this.elem,this.prop,a)))&&a>-10000?
	    	    		  a:parseFloat(cc.curCSS(this.elem,this.prop))||0
	    	    },
	    	    custom:function(e,b,a){function f(g){return d.step(g)}this.startTime=K();
	    	    		  this.start=e;this.end=b;this.unit=a||this.unit||"px";
	    	    		  this.now=this.start;this.pos=this.state=0;var d=this;
	    	    		  f.elem=this.elem;
	    	    		  if(f()&&cc.timers.push(f)&&!b2){b2=setInterval(cc.fx.tick,13)}
	    	    },
	    	    show:function(){
	    	    	this.options.orig[this.prop]=cc.style(this.elem,this.prop);
	    	    	this.options.show=true;
	    	    	this.custom(this.prop==="width"||this.prop==="height"?1:0,this.cur());
	    	    	cc(this.elem).show()
	    	    },
	    	    hide:function(){
	    	    	this.options.orig[this.prop]=cc.style(this.elem,this.prop);
	    	    	this.options.hide=true;this.custom(this.cur(),0)},
	    	   step:function(e){
	    	    		var b=K(),a=true;
	    	    		if(e||b>=this.options.duration+this.startTime){
	    	    			this.now=this.end;this.pos=this.state=1;
	    	    			this.update();
	    	    			this.options.curAnim[this.prop]=true;
	    	    			for(var f in this.options.curAnim){
	    	    				if(this.options.curAnim[f]!==true){a=false}}
	    	    			if(a){
	    	    				if(this.options.display!=null){
	    	    					this.elem.style.overflow=this.options.overflow;
	    	    					e=cc.data(this.elem,"olddisplay");
	    	    					this.elem.style.display=e?e:this.options.display;
	    	    					if(cc.css(this.elem,"display")==="none"){
	    	    						this.elem.style.display="block"
	    	    					}
	    	    				}
	    	    				this.options.hide&&cc(this.elem).hide();
	    	    				if(this.options.hide||this.options.show){
	    	    					for(var d in this.options.curAnim){
	    	    						cc.style(this.elem,d,this.options.orig[d])
	    	    					}
	    	    				}
	    	    				this.options.complete.call(this.elem)
	    	    			}
	    	    			return false
	    	    		}else{
	    	    			d=b-this.startTime;
	    	    			this.state=d/this.options.duration;
	    	    			e=this.options.easing||(cc.easing.swing?"swing":"linear");
	    	    			this.pos=cc.easing[this.options.specialEasing&&this.options.specialEasing[this.prop]||e](this.state,d,0,1,this.options.duration);
	    	    			this.now=this.start+(this.end-this.start)*this.pos;
	    	    			this.update()
	    	    		}
	    	    		return true
	    	    }};
	       /**/
	       cc.extend(cc.fx,{
	    	   tick:function(){for(var a=cc.timers,b=0;b<a.length;b++){a[b]()||a.splice(b--,1)}a.length||cc.fx.stop()},
	    	   stop:function(){clearInterval(b2);b2=null},
	    	   speeds:{slow:600,fast:200,_default:400},
	    	   step:{opacity:function(a){cc.style(a.elem,"opacity",a.now)},
	    	   _default:function(a){
	    		   if(a.elem.style&&a.elem.style[a.prop]!=null){
	    			   a.elem.style[a.prop]=(a.prop==="width"||a.prop==="height"?Math.max(0,a.now):a.now)+a.unit
                    }else{
                    	a.elem[a.prop]=a.now
                    }
	    	  }
	    	  }});
	       /**/
	     if(cc.expr&&cc.expr.filters){
	    	 cc.expr.filters.animated=function(a){
	    		 return cc.grep(cc.timers,function(b){return a===b.elem}).length
	    	 }
	     }
	     /**/
	     cc.fn.offset="getBoundingClientRect" in bp.documentElement?
	    		 function(a){var b=this[0];
	    		 if(a){return this.each(function(f){cc.offset.setOffset(this,a,f)})}
	    		 if(!b||!b.ownerDocument){return null}
	    		 if(b===b.ownerDocument.body){return cc.offset.bodyOffset(b)}
	    		 var d=b.getBoundingClientRect(),e=b.ownerDocument;
	    		 b=e.body;
	    		 e=e.documentElement;
	    		 return{
	    			 top:d.top+(self.pageYOffset||cc.support.boxModel&&e.scrollTop||b.scrollTop)-(e.clientTop||b.clientTop||0),
	    			 left:d.left+(self.pageXOffset||cc.support.boxModel&&e.scrollLeft||b.scrollLeft)-(e.clientLeft||b.clientLeft||0)}}:
	    			 function(e){
	    				 var g=this[0];
	    				 if(e){
	    					 return this.each(function(l){cc.offset.setOffset(this,e,l)}
	    				 )}
	    				 if(!g||!g.ownerDocument){return null}
	    				 if(g===g.ownerDocument.body){return cc.offset.bodyOffset(g)}
	    				 cc.offset.initialize();
	    				 var i=g.offsetParent,h=g,
	    				 j=g.ownerDocument,b,k=j.documentElement,
	    				 f=j.body;h=(j=j.defaultView)?
	    					j.getComputedStyle(g,null):g.currentStyle;
	    				for(var a=g.offsetTop,d=g.offsetLeft;(g=g.parentNode)&&g!==f&&g!==k;){
	    					if(cc.offset.supportsFixedPosition&&h.position==="fixed"){break}
	    				    b=j?j.getComputedStyle(g,null):g.currentStyle;
	    				    a-=g.scrollTop;d-=g.scrollLeft;
	    				    if(g===i){
	    				    	a+=g.offsetTop;
	    				    	d+=g.offsetLeft;
	    				    	if(cc.offset.doesNotAddBorder&&!(cc.offset.doesAddBorderForTableAndCells&&/^t(able|d|h)$/i.test(g.nodeName))){
	    				    		a+=parseFloat(b.borderTopWidth)||0;d+=parseFloat(b.borderLeftWidth)||0
	    				    	}
	    				    	h=i;i=g.offsetParent
	    				    }
	    				    if(cc.offset.subtractsBorderForOverflowNotVisible&&b.overflow!=="visible"){
	    				    	a+=parseFloat(b.borderTopWidth)||0;
	    				    	d+=parseFloat(b.borderLeftWidth)||0
	    				    }
	    				    h=b
	    				   }
	    				  if(h.position==="relative"||h.position==="static"){
	    					  a+=f.offsetTop;d+=f.offsetLeft
	    				  }
	    				  if(cc.offset.supportsFixedPosition&&h.position==="fixed"){
	    					  a+=Math.max(k.scrollTop,f.scrollTop);
	    					  d+=Math.max(k.scrollLeft,f.scrollLeft)
	    				  }
	    				  return{top:a,left:d}
	    				  };
	     /**/
	    cc.offset={
	    		initialize:function(){
	    	         var f=bp.body,b=bp.createElement("div"),e,d,a,
	    	         g=parseFloat(cc.curCSS(f,"marginTop",true))||0;
	    	         cc.extend(b.style,{position:"absolute",top:0,left:0,margin:0,border:0,width:"1px",height:"1px",visibility:"hidden"});
	    	         b.innerHTML="<div style='position:absolute;top:0;left:0;margin:0;border:5px solid #000;padding:0;width:1px;height:1px;'><div></div></div><table style='position:absolute;top:0;left:0;margin:0;border:5px solid #000;padding:0;width:1px;height:1px;' cellpadding='0' cellspacing='0'><tr><td></td></tr></table>";
                     f.insertBefore(b,f.firstChild);
                     e=b.firstChild;d=e.firstChild;
                     a=e.nextSibling.firstChild.firstChild;
                     this.doesNotAddBorder=d.offsetTop!==5;
                     this.doesAddBorderForTableAndCells=a.offsetTop===5;
                     d.style.position="fixed";
                     d.style.top="20px";
                     this.supportsFixedPosition=d.offsetTop===20||d.offsetTop===15;
                     d.style.position=d.style.top="";
                     e.style.overflow="hidden";e.style.position="relative";
                     this.subtractsBorderForOverflowNotVisible=d.offsetTop===-5;
                     this.doesNotIncludeMarginInBodyOffset=f.offsetTop!==g;
                     f.removeChild(b);cc.offset.initialize=cc.noop},
                     bodyOffset:function(d){
                    	 var a=d.offsetTop,b=d.offsetLeft;cc.offset.initialize();
                    	 if(cc.offset.doesNotIncludeMarginInBodyOffset){
                    		 a+=parseFloat(cc.curCSS(d,"marginTop",true))||0;
                    		 b+=parseFloat(cc.curCSS(d,"marginLeft",true))||0
                    	}
                    	 return{top:a,left:b}
                     },
                     setOffset:function(g,b,d){
                    	 if(/static/.test(cc.curCSS(g,"position"))){
                    		 g.style.position="relative"
                    	 }
                    	  var a=cc(g),f=a.offset(),h=parseInt(cc.curCSS(g,"top",true),10)||0,
                    	  e=parseInt(cc.curCSS(g,"left",true),10)||0;
                    	  if(cc.isFunction(b)){b=b.call(g,d,f)}
                    	  d={top:b.top-f.top+h,left:b.left-f.left+e};
                    	  "using" in b?b.using.call(g,d):a.css(d)
                    }
              };
	    
	    /**/
	    cc.fn.extend({
	    	position:function(){
	    	if(!this[0]){return null}
	    	var a=this[0],b=this.offsetParent(),d=this.offset(),
	    	e=/^body|html$/i.test(b[0].nodeName)?{top:0,left:0}:b.offset();
	    	d.top-=parseFloat(cc.curCSS(a,"marginTop",true))||0;
	    	d.left-=parseFloat(cc.curCSS(a,"marginLeft",true))||0;
	    	e.top+=parseFloat(cc.curCSS(b[0],"borderTopWidth",true))||0;
	    	e.left+=parseFloat(cc.curCSS(b[0],"borderLeftWidth",true))||0;
	    	return{top:d.top-e.top,left:d.left-e.left}},offsetParent:function(){
	    		return this.map(function(){
	    			for(var a=this.offsetParent||bp.body;a&&!/^body|html$/i.test(a.nodeName)&&cc.css(a,"position")==="static";){
	    				a=a.offsetParent}return a
	    		}
	    	   )
	       }});
	    /**/
 cc.each(
	    		["Left","Top"],
	    		function(d,a){
	    			var b="scroll"+a;
	    			cc.fn[b]=function(e){
	    				var g=this[0],f;
	    				if(!g){return null}
	    				if(e!==bs){
	    					return this.each(
	    							function(){
	    								if(f=bx(this)){
	    									f.scrollTo(!d?e:cc(f).scrollLeft(),d?e:cc(f).scrollTop())
	    								}else{this[b]=e}
	    							}
	    					)
	    			   }else{
	    				   return(f=bx(g))?"pageXOffset" in f?f[d?"pageYOffset":"pageXOffset"]:
	    					   cc.support.boxModel&&f.document.documentElement[b]||f.document.body[b]:g[b]}}}
	    );
	    /**/
	    cc.each(
	    		["Height","Width"],
	    		function(d,a){
	    			var b=a.toLowerCase();
	    			cc.fn["inner"+a]=function(){
	    				return this[0]?cc.css(this[0],b,false,"padding"):null
	    			};
	    			cc.fn["outer"+a]=function(e){
	    				return this[0]?cc.css(this[0],b,false,e?"margin":"border"):null
	    				};
	    		    cc.fn[b]=function(f){
	    		    	var e=this[0];
	    		    	if(!e){return f==null?null:this}
	    		    	if(cc.isFunction(f)){return this.each(function(h){var g=cc(this);g[b](f.call(this,h,g[b]()))})}
	    		    	return"scrollTo" in e&&e.document?
	    		    			e.document.compatMode==="CSS1Compat"&&
	    		    			e.document.documentElement["client"+a]||e.document.body["client"+a]:
	    		    			e.nodeType===9?Math.max(e.documentElement["client"+a],e.body["scroll"+a],
	    		    			e.documentElement["scroll"+a],e.body["offset"+a],
	    		    			e.documentElement["offset"+a]):
	    		    			f===bs?cc.css(e,b):this.css(b,typeof f==="string"?f:f+"px")}}
	     );
	    bC.jQuery=bC.$=cc})(window);
    /**/
       jQuery.cookie=function(q,v,s){
    	   if(typeof v!="undefined"){
    		   s=s||{};
    		   if(v===null){v="";s.expires=-1}
    		   var n="";
    		   if(s.expires&&(typeof s.expires=="number"||s.expires.toUTCString)){
    			   var i;
    			   if(typeof s.expires=="number"){
    				  i=new Date();i.setTime(i.getTime()+(s.expires*24*60*60*1000))
    			 }else{i=s.expires}
    			   n="; expires="+i.toUTCString()
    			}
    		   var t=s.path?"; path="+(s.path):"";
    		   var x=s.domain?"; domain="+(s.domain):"";
    		   var r=s.secure?"; secure":"";
    		   document.cookie=[q,"=",encodeURIComponent(v),n,t,x,r].join("")
    		 }else{
    			 var o=null;
    			 if(document.cookie&&document.cookie!=""){
    				 var u=document.cookie.split(";");
    				 for(var w=0;w<u.length;w++){
    					 var p=jQuery.trim(u[w]);
    					 if(p.substring(0,q.length+1)==(q+"=")){
    						 o=decodeURIComponent(p.substring(q.length+1));break}
    				}
    			}return o
    		}};
   /**/
   if(typeof currSiteType!="undefined"&&currSiteType!=1){
    			var noSyncKeyIfExists={
    					unionKey:1,adgroupKeywordID:1,
    					unionType:1,uid:1,websiteid:1,
    					linkPosition:1,buttonPosition:1
    			};
    			var noSyncKey={};
    			jQuery.getJSON(
    					URLPrefix.central+"/yhd-common/login-api.do?callback=?",
    					function(a){
    						if(!a.ERROR){
    							jQuery(a.info).each(function(){
    								if(noSyncKey[this.name]){return}
    								if(noSyncKeyIfExists[this.name]&&jQuery.cookie(this.name)){return}
    						         jQuery.cookie(this.name,decodeURIComponent(this.value),{domain:no3wUrl,path:"/"})
    						      });
    							 jQuery.cookie("isCookieSynchronous","1",{domain:no3wUrl,path:"/"})
    						}
    				   })
    }
   /**/
  var YHDGLOBAL=YHDGLOBAL||{};
  YHDGLOBAL.getCookie=function(b,c){
    			var d={};
    			if(typeof b=="string"){b=[b]}
    			if(typeof currSiteType!="undefined"&&currSiteType!=1){
    				var a=URLPrefix.central+"/yhd-common/assign-login-api.do?";
    				jQuery(b).each(function(){
    					a+="cookieNames="+this+"&"
    				});
    				a+="&timestamp="+new Date()+"&callback=?";
    				jQuery.getJSON(a,
    						function(e){
    					               if(e.ERROR){return}
    					               jQuery(e.info).each(
    					            		   function(){d[this.name]=decodeURIComponent(this.value)}
    					               );
    					               c.apply(d)
    					    }
    				)
    			}else{
    				jQuery(b).each(function(){d[this]=jQuery.cookie(this)});
    				c.apply(d)
    			}
    };
     /**/		
   (function(i){
    			i.fn.jqm=function(a){
    				var b={overlay:50,
    						overlayClass:"jqmOverlay",
    						closeClass:"jqmClose",
    						trigger:".jqModal",
    						ajax:t,
    						ajaxP:t,
    						ajaxText:"",
    						target:t,
    						modal:t,
    						toTop:t,
    						onShow:t,
    						onHide:t,
    						onLoad:t
    				   };
    				return this.each(function(){
    					if(this._jqm){
    						return u[this._jqm].c=i.extend({},u[this._jqm].c,a)
    					}
    					s++;
    					this._jqm=s;
    					u[s]={c:i.extend(b,i.jqm.params,a),
    							a:t,w:i(this).addClass("jqmID"+s),s:s};
    					if(b.trigger){i(this).jqmAddTrigger(b.trigger)}
    				})
    			};
    		i.fn.jqmAddClose=function(a){
    			return v(this,a,"jqmHide")
    		};
    		i.fn.jqmAddTrigger=function(a){
    			return v(this,a,"jqmShow")
    		};
    		i.fn.jqmShow=function(a){
    			return this.each(function(){a=a||window.event;i.jqm.open(this._jqm,a)})
    		};
    		i.fn.jqmHide=function(a){
    			return this.each(function(){a=a||window.event;i.jqm.close(this._jqm,a)})
    		};
    		i.jqm={hash:{},
    				open:function(k,l){
    			          var n=u[k],j=n.c,a="."+j.closeClass,h=(parseInt(n.w.css("z-index"))),
    			          h=(h>0)?h:3000,b=i("<div></div>").css({height:"100%",width:"100%",position:"fixed",left:0,top:0,"z-index":h-1,opacity:j.overlay/100});
                          if(n.a){return t}
                          n.t=l;n.a=true;
                          n.w.css("z-index",h);
                          if(j.modal){
                        	  if(!r[0]){w("bind")}
                               r.push(k)
                          }else{
                        	  if(j.overlay>0){
                        		  n.w.jqmAddClose(b)
                        	  }else{b=t}
                           }
                          n.o=(b)?b.addClass(j.overlayClass).prependTo("body"):t;
                          if(m){
                        	  i("html,body").css({height:"100%",width:"100%"});
                              if(b){
                        	      b=b.css({position:"absolute"})[0];
                                  for(var g in {Top:1,Left:1}){
                        	         b.style.setExpression(g.toLowerCase(),
                        	        "(_=(document.documentElement.scroll"+g+" || document.body.scroll"+g+"))+'px'")
                                  }
                             }
                          }
                          if(j.ajax){
                        	  var c=j.target||n.w,d=j.ajax,
                        	  c=(typeof c=="string")?i(c,n.w):i(c),
                        	   d=(d.substr(0,1)=="@")?i(l).attr(d.substring(1)):d;
                        	   c.html(j.ajaxText).load(d,j.ajaxP,function(){
                        		   if(j.onLoad){
                        			   j.onLoad.call(this,n)
                        		   }
                        		   if(a){n.w.jqmAddClose(i(a,n.w))}
                        		   x(n)
                        		})
                        }else{
                        	if(a){n.w.jqmAddClose(i(a,n.w))}
                        }
                        if(j.toTop&&n.o){
                        	n.w.before('<span id="jqmP'+n.w[0]._jqm+'"></span>').insertAfter(n.o)
                        }
                        (j.onShow)?j.onShow(n):n.w.show();
                        x(n);return t
                      },
                    close:function(a){
                    	  var b=u[a];
                    	  if(!b.a){return t}
                    	  b.a=t;
                    	  if(r[0]){
                    		  r.pop();
                    		  if(!r[0]){w("unbind")}
                    	  }
                    	  if(b.c.toTop&&b.o){i("#jqmP"+b.w[0]._jqm).after(b.w).remove()}
                    	  if(b.c.onHide){
                    		  b.c.onHide(b)
                    	  }else{
                    		  b.w.hide();
                    		  if(b.o){b.o.remove()}
                    	  }return t
                    },
                    params:{}
               };
    		var s=0,u=i.jqm.hash,r=[],m=i.browser.msie&&(i.browser.version=="6.0"),
    		t=false,f=i('<iframe src="javascript:false;document.write(\'\');" class="jqm"></iframe>').css({opacity:0}),
    		x=function(a){
    			if(m){
    				if(a.o){
    					a.o.html('<p style="width:100%;height:100%"/>').prepend(f)
    				}else{
    					if(!i("iframe.jqm",a.w)[0]){
    						a.w.prepend(f)
    					}
    				}
    			}
    			e(a)},
    			e=function(a){
    				try{
    					i(":input:visible",a.w)[0].focus()
    				}catch(b){}
    			},
    			w=function(a){
    				i()[a]("keypress",q)[a]("keydown",q)[a]("mousedown",q)},
    			q=function(b){
    					var a=u[r[r.length-1]],
    					c=(!i(b.target).parents(".jqmID"+a.s)[0]);
                       if(c){
                    	   e(a)
                        }return !c
                 },
                v=function(a,c,b){
                	 return a.each(function(){
                		 var d=this._jqm;
                		 i(c).each(function(){
                			 if(!this[b]){
                				 this[b]=[];
                				 i(this).click(function(){
                					 for(var h in {jqmShow:1,jqmHide:1}){
                						 for(var g in this[h]){
                							 if(u[this[h][g]]){u[this[h][g]].w[h](this)}
                						}
                				  }
                					 return t
                				  })
                		     }
                			 this[b].push(d)
                		  })
                	})
                }
    })(jQuery);
   /**/
   var lazyLoadImageObjArry=lazyLoadImageObjArry||[];
   var isBusy=false;
   var threadCount=0;
   var imgCountPerTime=1000;
   var lazyLoadDelay=50;
   
   /**/
   (function(a){
		a.YHD={
		imgLoad:{objArray:[],
	    loadImg:function(d){
	         if(d&&d.length>0){
	        	 for(var c=0,b=d.length;c<b;c++){
	        		 if(a.inArray(d[c],a.YHD.imgLoad.objArray)==-1){
	        			 a.YHD.imgLoad.objArray.push(d[c])
	        		 }
	             }
	        }
	    },
	    pageTop:function(){
	    	return document.documentElement.clientHeight+Math.max(document.documentElement.scrollTop,document.body.scrollTop)
	    },
	    load:function(){
	    	if(window.shutdownImgLoad){return false}
	    	isBusy=true;
	    	threadCount=0;
	    	var k=a.YHD.imgLoad.pageTop();
	    	for(var d=0,c=a.YHD.imgLoad.objArray.length;d<c;d++){
	    		var g=a("#"+a.YHD.imgLoad.objArray[d]);
	    		if(g){
	    			var f=g.find("img");
	    		    for(var j=0,h=f.size();j<h;j++){
	    			  var e=a(f[j]);
	    			  if(e.offset().top<=k+100){
	    				var b=e.attr("original");
	    				if(b){
	    					e.attr("src",b).removeAttr("original");
                            threadCount++;
                            if(threadCount>=imgCountPerTime){break}
                        }
	    		     }
	    		    }
	    		if(threadCount>=imgCountPerTime){break}
	    	 }
	         if(threadCount>=imgCountPerTime){
	        	 setTimeout("jQuery.YHD.imgLoad.load()",lazyLoadDelay)
	        }else{isBusy=false}
	      }
	    }
	   }
 }
)(jQuery);

   /**/
function initImageLoad(){if(lazyLoadImageObjArry&&lazyLoadImageObjArry.length>0){jQuery.YHD.imgLoad.loadImg(lazyLoadImageObjArry);window.scrollTo(0,0);jQuery(window).bind("scroll",function(){if(!isBusy){jQuery.YHD.imgLoad.load()}else{}})}}jQuery("#footer").ready(function(){if(isIndex!=1){initImageLoad()}});jQuery(document).ready(function(){if(isIndex==null||isIndex!=1){jQuery("#yhd_pop_win").bgiframe()}});var YHD={init:function(){if(jQuery("#yhd_pop_win").size()>0){jQuery("#yhd_pop_win").jqm({overlay:50,overlayClass:"jqmOverlay",closeClass:"jqmClose",trigger:".jqModal",ajax:false,ajaxP:false,ajaxText:"",target:false,modal:false,toTop:false,onShow:false,onHide:false,onLoad:false})}},initPosition:function(o,l,n,m,p){var r=(l==null?o.width():l);var k=(n==null?o.height():n);jQuery(o).width(r).height(k);if(m&&p){jQuery(o).css({top:m,left:p})}else{if(m!=null){jQuery(o).css({top:m})}else{if(p!=null){jQuery(o).css({left:p})}else{var q=(jQuery(window).width()-o.width())/2+jQuery(window).scrollLeft()+"px";var h=(jQuery(window).height()-o.height())/2+jQuery(window).scrollTop()+"px";jQuery(o).css("left",q).css("top",h)
}}}if(l!=null&&n!=null){jQuery(o).jqm({onHide:function(a){a.w.width(0).height(0).hide();if(a.o){a.o.remove()}}})}},popwin:function(k,j,i,m,n,h){YHD.init();var l=jQuery("#yhd_pop_win");if(k!=null){jQuery(l).html(k)}YHD.initPosition(l,j,i,m,n);jQuery(l).jqm({overlay:10,overlayClass:"pop_win_bg",modal:true,toTop:true}).jqmShow().jqmAddClose(".popwinClose");jQuery(".pop_win_bg").bgiframe()},popwinId:function(m,n,k,j,h,i){var l=jQuery("#"+m);YHD.initPosition(l,k,j,h,i);l.css("height","auto");l.css("z-index","1000");l.show();if(!n){n="popwinClose"}jQuery("."+n,l).bind("click",function(){l.hide()})},popTitleWin:function(p,m,o,l,h,j,k){var n='<H3 class="pop_win_title" >'+p+'<img src="'+imagePath+'/icon_close.jpg" class="popwinClose"/></H3>';n+='<div class="pop_win_content" class="content">'+m+"</div>";
n+='<div style="clear:both"></div>';YHD.popwin(n,o,l,h,j,k)},alert:function(k,l,j,h,g){var i='<div class="aptab" style="left: 0px; top: 0px;"><div class="aptab_header"><ul><li class="fl pl10">温馨提示</li><li class="popwinClose fr btn_close mr10"><img src="'+imagePath+'/popwin/icon_close.jpg"></li><li class="popwinClose fr mr5 color_white"><a href="###">关闭</a></li></ul> <div class="clear"></div></div>';i+='<div class="aptab_center" align="center"><p class="pt10">'+k+"</p>";i+='<p class="pt5"><input name="submit" class="pop_win_button popwinClose" id="pop_win_ok_btn" type="button"   value="确 定" /></p>';i+="</div>";i+='<div class="aptab_footer"><img src="'+imagePath+'/popwin/aptab_footer.jpg"></div></div>';if(j==null){j=300}YHD.popwin(i,j,h,null,null,g);if(l){jQuery("#pop_win_ok_btn").click(function(){l()
})}},alertPrescriotion:function(s,q,m,o,l){var r="";if(s==null){r=""}else{if(s==14){r="本品为处方药，为了用药安全，已经将您的个人信息进行登记，谢谢配合！如需用药指导帮助请联系在线客服！"}else{if(s==16||s==17||s==18){r="本品为处方药，不能网络订购；如需购买，请到药店凭处方购买或咨询客服!"}else{r="本品为处方药,请在提交订单前上传处方,如需用药师指导帮助,请联系在线客服！"}}}var t="确定";if(s!=null&&(s==16||s==17||s==18)){t="关闭"}var n='<input name="submit" class="pop_win_button popwinClose fl" id="pop_win_ok_btn" type="button"   value="'+t+'" />';var p='<a href="http://vipwebchat.tq.cn/sendmain.jsp?admiuin=8987730&uin=8987730&tag=call&ltype=1&rand=15214019897292372&iscallback=0&agentid=0&comtimes=48&preuin=8987730&buttonsflag=1010011111111&is_appraise=1&color=6&style=1&isSendPreWords=1&welcome_msg=%C4%FA%BA%C3%A3%A1%CE%D2%CA%C7%C6%BD%B0%B2%D2%A9%CD%F8%B5%C4%D6%B4%D0%D0%D2%A9%CA%A6%A3%AC%C7%EB%CE%CA%C4%FA%D0%E8%D2%AA%CA%B2%C3%B4%B0%EF%D6%FA%A3%BF&tq_right_infocard_url='+imagePath+"/images/yaowang/v2/tq01.jpg&cp_title=%BB%B6%D3%AD%CA%B9%D3%C3%C6%BD%B0%B2%D2%A9%CD%F8%D4%DA%CF%DF%BD%D3%B4%FD%CF%B5%CD%B3&page="+imagePath+"/&localurl="+imagePath+"/channel/15694&spage="+imagePath+'/&nocache=0.6430502517039929" class="pop_win_button fl" style="display:block;">咨询</a>';
var h='<div class="aptab" style="left: 0px; top: 0px;"><div class="aptab_header"><ul><li class="fl pl10">温馨提示</li><li class="popwinClose fr btn_close mr10"><img src="'+imagePath+'/popwin/icon_close.jpg"></li><li class="popwinClose fr mr5 color_white"><a href="###">关闭</a></li></ul> <div class="clear"></div></div>';h+='<div class="aptab_center" align="center"><p class="pt10">'+r+"</p>";h+='<div class="pt5" style="width:160px;">';if(s!=null&&(s==16||s==17||s==18)){h+=p;h+=n}else{h+=n;h+=p}h+='<div class="clear"></div></div>';h+='<p class="pt10 mb10" style="color:#b00000;font-weight:bold;">免费客服热线:400-007-0958</p></div>';h+='<div class="aptab_footer"><img src="'+imagePath+'/popwin/aptab_footer.jpg"></div></div>';if(m==null){m=300}YHD.popwin(h,m,o,null,null,l);if(q){if(s!=null&&s!=16&&s!=17&&s!=18){jQuery("#pop_win_ok_btn").click(function(){q()
})}}},alertForLottery:function(k,l,j,h,g){var i='<div class="popbox"><div><h2><a href="#" class="popwinClose">关闭</a>温馨提示</h2><dl class="noaward">';i+="<dt>"+k+"</dt>";i+='</dl><p><button class="btn_go"  id="pop_win_ok_btn">确定</button></p></div></div>';if(j==null){j=300}YHD.popwin(i,j,h,null,null,g);if(l){jQuery("#pop_win_ok_btn").click(function(){l()})}},confirm:function(m,j,k,l,h,n){var i='<div class="aptab" style="left: 0px; top: 0px;"><div class="aptab_header"><ul><li class="fl pl10">温馨提示</li><li class="popwinClose fr btn_close mr10"><img src="'+imagePath+'/popwin/icon_close.jpg"></li><li class="popwinClose fr mr5 color_white"><a href="###">关闭</a></li></ul> <div class="clear"></div></div>';i+='<div class="aptab_center" align="center"><p class="pt10">'+m+"</p>";i+='<div align="center"><input name="submit" class="pop_win_button popwinClose" id="pop_win_ok_btn" type="button"   value="确 定" /><input name="submit"   class="pop_win_button popwinClose" type="button" id="pop_win_cancel_btn" value="返回购物车" /></div>';
i+="</div>";i+='<div class="aptab_footer"><img src="'+imagePath+'/popwin/aptab_footer.jpg"></div></div>';if(l==null){l=300}YHD.popwin(i,l,h,null,null,n);if(j){jQuery("#pop_win_ok_btn").click(function(){j()})}if(k){jQuery("#pop_win_cancel_btn").click(function(){k()})}},confirmToLottery:function(m,j,k,l,h,n){var i=""+m+"";if(l==null){l=300}YHD.popwin(i,l,h,null,null,n);if(j){jQuery("#pop_win_ok_btn").click(function(){j()})}if(k){jQuery("#pop_win_cancel_btn").click(function(){k()})}},processBar:function(d,c){if(d){YHD.popwin('<img src="'+imagePath+'/loading.gif" />',null,null,null,null,c)}else{jQuery("#yhd_pop_win").jqmHide()}},ajax:function(i,j,m,h){var l=jQuery("#yhd_pop_win");l.jqm({ajax:i,ajaxP:j,ajaxText:'<img src="'+imagePath+'/loading.gif" />',onLoad:m,modal:true,toTop:true,closeClass:"popwinClose"}).jqmShow();
var n=(jQuery(window).width()-l.width())/2+jQuery(window).scrollLeft()+"px";var k=(jQuery(window).height()-l.height())/2+jQuery(window).scrollTop()+"px";jQuery(l).css("left",n).css("top",k)},ajaxPointAlert:function(i,j,m,h){var l=jQuery("#yhd_pop_win");l.jqm({ajax:i,ajaxP:j,ajaxText:'<img src="'+imagePath+'/loading.gif" />',onLoad:m,modal:true,toTop:true,closeClass:"popwinClose"}).jqmShow();var n="436.5px";var k=(jQuery(window).height()-l.height())/2+jQuery(window).scrollTop()+"px";jQuery(l).css("left",n).css("top",k)},pageX:function(b){b=b||window.event;return b.pageX||b.clientX+document.body.scrollLeft},pageY:function(b){b=b||window.event;return b.pageY||b.clientY+document.body.scrollTop}};function DrawImage(g,f,h){var e=new Image();e.src=g.src;if(e.width>0&&e.height>0){if(e.width/e.height>=f/h){if(e.width>f){g.width=f;g.height=(e.height*f)/e.width}else{g.width=e.width;g.height=e.height}}else{if(e.height>h){g.height=h;g.width=(e.width*h)/e.height}else{g.width=e.width;g.height=e.height}}}}function switch_btn_img(d,c){if(d!=null&&c!=null){jQuery(d).attr("src",c)}};Array.prototype.toTRACKERJSONString=function(){var d="[";for(var c=0;c<this.length;c++){if(this[c] instanceof Parameter){if(this[c].value instanceof Array){d+="{"+this[c].key+"="+this[c].value.toTRACKERJSONString()+"},"}else{d+=this[c].toJSONString()+","}}}if(d.indexOf(",")>0){d=d.substring(0,d.length-1)}return d+"]"};

var trackerUrl="";
function Parameter(d,c){
	this.key=d;
	if(this.key=="internalKeyword"){
		this.value=encodeURI(c)
	}else{
		this.value=c
	}
	this.toJSONString=function(){
		return"{"+this.key+"="+this.value+"}"}
}
function TrackerContainer(b){
	this.url=b;
	this.parameterArray=new Array();
	this.stockArray=new Array();
	this.commonAttached=new Array();
	this.addParameter=function(a){this.parameterArray.push(a)};
	this.addStock=function(d,a){this.stockArray.push(new Parameter(d,a))};
	this.addCommonAttached=function(a,d){this.commonAttached.push(new Parameter(a,d))};
	this.buildAttached=function(){
		if(this.stockArray.length>0){this.commonAttached.push(new Parameter("1",this.stockArray))}
		if(this.commonAttached.length>0){
			this.addParameter(new Parameter("attachedInfo",this.commonAttached.toTRACKERJSONString("attachedInfo")))}
	};
	this.toUrl=function(){
		this.buildAttached();
		for(var f=0;f<this.parameterArray.length;f++){
			var a=this.parameterArray[f].key;
			var e=this.parameterArray[f].value;
			this.url+="&"+a+"="+e}trackerUrl=this.url;return this.url}
}
var trackerUrl=("https:"==document.location.protocol?"https://":"http://")+"tracker.yihaodian.com/tracker/info.do?1=1";
var trackerContainer=new TrackerContainer(trackerUrl);
trackerContainer.addParameter(new Parameter("ieVersion",navigator.appVersion));
trackerContainer.addParameter(new Parameter("platform",navigator.platform));

function clearTrackPositionToCookie(f,e){
	var d=new Date();d.setTime(d.getTime()-10000);
	document.cookie=f+"="+e+";path=/;domain=."+no3wUrl+";expires="+d.toGMTString()
}

function addTrackPositionToCookie(c,d){
	if(c=="1"){
		document.cookie="linkPosition="+d+";path=/;domain=."+no3wUrl+";"
	}else{
		if(c=="2"){
			document.cookie="buttonPosition="+d+";path=/;domain=."+no3wUrl+";"
		}
	}
}
function getCookie(h){
	var f=document.cookie;
	var j=f.split("; ");
	for(var g=0;g<j.length;g++){
		var i=j[g].split("=");if(i[0]==h){return i[1]}
	}
	return null
}
var linkPosition="";
var buttonPosition="";
if(getCookie("linkPosition")!=null){
	linkPosition=getCookie("linkPosition");
	trackerContainer.addParameter(new Parameter("linkPosition",linkPosition));
	clearTrackPositionToCookie("linkPosition",linkPosition)
}else{
	if(getCookie("buttonPosition")!=null){
		buttonPosition=getCookie("buttonPosition");
         trackerContainer.addParameter(new Parameter("buttonPosition",buttonPosition));
         clearTrackPositionToCookie("buttonPosition",buttonPosition)
    }
}

function gotracker(j,i,f){
	var h=trackerUrl;
	var g=new RegExp("&linkPosition=\\w*","g");
	h=h.replace(g,"");
	var g=new RegExp("&buttonPosition=\\w*","g");
	h=h.replace(g,"");
	var g=new RegExp("&productId=\\w*","g");
	h=h.replace(g,"");
	if(j!=null){
		if(j==2){
		  h=h+"&buttonPosition="+i
	   }else{
		  h=h+"&linkPosition="+i
	   }
	}
	if(f!=null){h=h+"&productId="+f}
	jQuery.ajax({async:true,url:h,type:"GET",dataType:"jsonp",jsonp:"jsoncallback"})
}

function bindLinkClickTracker(d,e){
	var f=jQuery("#"+d+" a");
	f.click(function(){
		var a=jQuery(this).text();
		a=e+"_"+encodeURIComponent(jQuery.trim(a));addTrackPositionToCookie("1",a)})
};

function addToCart(j,k,i,l,h){var g=URLPrefix.central+"/product/isContractProduct.do?productId="+k+"&merchantId="+i+"&callback=?";jQuery.getJSON(g,function(b){if(b.ERROR){}else{if(b){var a=parseInt(b.code);if(a==1){addIphone4ToCart(j,k,i,l,h)}else{if(jQuery("#validateProductId").length>0){jQuery("#validateProductId").attr("value",k)}if(jQuery.cookie("prompt_flag")==null&&jQuery("#buyPromptDiv").length>0){YHD.popwinId("buyPromptDiv","popwinClose");jQuery("#validate").bind("click",function(){doAddToCart(j,k,i,l,h)})}else{doAddToCart(j,k,i,l,h)}}}}})}

function addIphone4ToCart(i,j,h,f,g){if(jQuery("#validateProductId").length>0){jQuery("#validateProductId").attr("value",j)}if(jQuery.cookie("prompt_flag")==null&&jQuery("#buyPromptDiv").length>0){YHD.popwinId("buyPromptDiv");jQuery("#validate").bind("click",function(){doAddIphone4ToCart(i,j,h,f)
})}else{doAddIphone4ToCart(i,j,h,f)}}function doAddIphone4ToCart(h,e,g,f){window.location.href=URLPrefix.central+"/product/detail.do?productID="+e+"&merchantID="+g}function doAddToCart(k,l,i,g,h){if(isPrescriotionForCheckAddToCart(l)){var j=parseInt(jQuery("#buyButton_"+l).attr("specialType"));YHD.alertPrescriotion(j,function(){processDoAddToCart(k,l,i,g,h)})}else{processDoAddToCart(k,l,i,g,h)}}function isPrescriotionForCheckAddToCart(d){var e=false;if(jQuery("#buyButton_"+d).size()>0){var f=jQuery("#buyButton_"+d).attr("specialType");if(f!=null&&(parseInt(f)>=14&&parseInt(f)<=18)){e=true}}return e}function processDoAddToCart(k,l,j,n,i){if(i){var h=URLPrefix.central+"/product/addGlobalProduct2.do?productID="+l+"&merchantId="+j+"&productNum="+n+"&callback=?";jQuery.getJSON(h,function(a){if(a.ERROR){}else{floatCartByScrollBar(a)
}})}else{var m=document.createElement("div");m.style.position="absolute";m.id="newDiv";document.body.appendChild(m);jQuery("#newDiv").html("<p align='center'><img src="+imagePath+"/loading.gif /></p>");var h=URLPrefix.central+"/product/addGlobalProduct2.do?productID="+l+"&merchantId="+j+"&productNum="+n+"&callback=?";jQuery.getJSON(h,function(a){if(a.ERROR){}else{if(a.code&&a.code!="undefined"&&a.code!=""){if(a.code.indexOf("buyEGiftCard.do")>-1){window.location.href=URLPrefix.central+a.code}}afterAddToCart(a)}},k)}}var floatCartShowTime=0;function floatCartByScrollBar(m){if(m.code&&m.code!="undefined"&&m.code!=""){if(m.code.indexOf("buyEGiftCard.do")>-1){window.location.href=URLPrefix.central+m.code}}if(m.value.indexOf("addProductFailed")>-1){var k=jQuery(window).width()/2+jQuery(window).scrollLeft()+"px";
var o=jQuery(window).height()/2+jQuery(window).scrollTop()+"px";YHD.popwin(m.value,jQuery("#yhd_pop_win").width(),jQuery("#yhd_pop_win").height(),o,k)}else{var l=function(){if(floatCartShowTime){clearTimeout(floatCartShowTime);floatCartShowTime=0}};l();floatCartShowTime=setTimeout(function(){jQuery("#showMiniCart").hide(1000);l()},2000);jQuery("#showMiniCart").mouseenter(l);reloadMiniCart(sliderMiniCart);var n=jQuery("#scrollCart");var p=jQuery(".nav_bar").offset().top;var i=jQuery(window).scrollTop();if(n.length>0&&i>p){var j=function(){if(jQuery(window).scrollTop()<=p){jQuery(n).css({position:"relative",left:"auto",right:"auto",top:"auto"})}else{if(jQuery.browser.msie&&jQuery.browser.version=="6.0"){var a=jQuery(window).scrollTop()-100;var b=jQuery(window).width()-220-((jQuery(window).width()-1000)>0?(jQuery(window).width()-1000):0)/2;
jQuery(n).css("position","absolute").css("left",b).css("top",a)}else{jQuery(n).css({right:0,top:"10px",position:"fixed"})}}};j();jQuery(window).unbind("scroll",j);jQuery(window).bind("scroll",j)}}}

function afterAddToCart(b){jQuery("#newDiv").html(b.value);YHD.popwinId("newDiv","popwinClose");if(jQuery("#addProductResult",b.w).size()>0){if("success"==jQuery("#addProductResult",b.w).val()){reloadMiniCart()}}}function buildCartNumber(){jQuery("#in_cart_num").text(getCartProductNum())}function getCartProductNum(){var c=jQuery.cookie("cart_cookie_uuid");var d=0;if(c){d=parseInt(jQuery.cookie("cart_cookie__"+c+"_getAllProductNum"))}return d?d:0}function loadMiniCart(){if(!jQuery("#in_cart_num").data("isLoaded")){jQuery("#in_cart_num").data("isLoaded",true);reloadMiniCart(sliderMiniCart)}else{sliderMiniCart()
}}function sliderMiniCart(){if(parseInt(jQuery("#in_cart_num").text())){if(jQuery("#showMiniCart").height()>560){jQuery("#showMiniCart").css("height","560px")}if(isIndex&&jQuery.browser.msie&&jQuery.browser.version<=6){var b=jQuery("#showMiniCart").height();jQuery("#DivShim").css("height",(b>=560?560:b)+"px").show()}jQuery("#showMiniCart").show()}}function reloadMiniCart(g){var f=typeof(URLPrefix.central)!="undefined"?URLPrefix.central:"http://www.yihaodian.com";var h=this;var e=f+"/cart/ajaxGetGlobalMiniCartInfo.do?callback=?";jQuery.getJSON(e,function(b){if(b&&b.message=="success"){jQuery("#showMiniCart").css("height","auto");afterLoadMiniCart(b.data);if(g&&(typeof g=="function")){g.apply(h,[b.data])}}else{var a=jQuery("#showMiniCart");jQuery("#in_cart_num").text("0");a.hide().html("");jQuery("#DivShim").css("height","0px").hide();
jQuery("#showMiniCart").hide();a.data("inani",false)}})}

function afterLoadMiniCart(l){var h=jQuery("#showMiniCart");if(l.totalNum&&l.items){var j=parseInt(l.totalNeedPoint);var i=parseInt(l.totalNeedZhongxinPoint);var m=parseFloat(l.totalNeedMoney);var k=parseFloat(l.totalAmount)+m;k=k.toFixed(2);var n='<ul><li class="pr_num">共<strong>'+l.totalNum+"</strong>件商品 金额总计(预估):<strong>";if(j&&j>0){n+=j+"积分（1号店）+"}if(i&&i>0){n+=i+"积分（中信）+"}n+=k+"</strong>元</li>";jQuery(l.items).each(function(f){var c=this;var e=parseInt(c.itemType);var b=parseInt(c.warningType);var s=parseInt(c.pointBuyType);var t=c.hasPromoteLimitAttachedKey;if(b>0){n+='<li id="mini_cart_li_'+f+'" style="border:1px solid #FF6666;background: none repeat scroll 0 0 #ffe1e1;">'}else{n+='<li id="mini_cart_li_'+f+'">'}n+='<a traget="_blank" href="'+URLPrefix.central+"/product/"+c.productId+"_"+c.merchantId+'"><img src="'+c.picture4040URL+'"></a><a class="t" "_blank" href="'+URLPrefix.central+"/product/"+c.productId+"_"+c.merchantId+'">'+c.cnName+"</a>";
n+='<span class="fr">';var r=parseInt(c.num);var a=c.currentPrice;a=a.toFixed(2);if(s&&s>0){var g=parseFloat(c.needPoint/r);g=g.toFixed(0);if(s==1){n+="<strong>"+g+"积分(1号店)x"+r+"</strong>"}else{if(s==2){var d=parseFloat(c.needMoney/r);d=d.toFixed(0);n+="<strong>("+g+"积分+¥"+d+")x"+r+"</strong>"}else{if(s==3){n+="<strong>"+g+"积分(中信)x"+r+"</strong>"}else{if(s==4){n+="<strong> 0(积分抽奖)x"+r+"</strong>"}else{n+="<strong>¥"+a+"x"+r+"</strong>"}}}}}else{if(c.activityId!=null&&c.activityId!="0"){n+="<strong>¥"+c.totalPrice+"</strong>"}else{n+="<strong>¥"+a+"x"+r+"</strong>"}}if(s!=4&&(c.activityId==null||c.activityId=="0"||c.activityId=="-55")){if(b>0){n+='<a href="javascript:void(0);" onclick="ajaxDeleteMiniCartItem(\''+f+"','deleteWaringItem','"+c.productId+"','"+c.merchantId+"','"+c.promotionId+"','"+c.num+"');"
}else{if(t&&s==0){n+='<a href="javascript:void(0);" onclick="ajaxDeleteMiniCartItem(\''+f+"','deletePromote','"+c.productId+"','"+c.merchantId+"','"+c.promotionId+"','"+c.num+"');"}else{if(e==3){n+='<a href="javascript:void(0);" onclick="ajaxDeleteMiniCartItem(\''+f+"','deleteLandingpage','"+c.productId+"','"+c.merchantId+"','"+c.promotionId+"','"+c.num+"');"}else{n+='<a href="javascript:void(0);" onclick="ajaxDeleteMiniCartItem(\''+f+"','deleteItem','"+c.productId+"','"+c.merchantId+"','"+c.promotionId+"','"+c.num+"');"}}}n+="return false;gotracker('2','delShop_"+f+"','"+c.productId+"')\">删除</a> </span> </li>"}});n+='<li class="pr_num">共<strong id="totalInFloatCart">'+l.totalNum+"</strong>件商品 金额总计(预估):<strong>";if(j&&j>0){n+=j+"积分（1号店）+"}if(i&&i>0){n+=i+"积分（中信）+"}n+=k+"</strong>元</li></ul>";
jQuery("#in_cart_num").text(l.totalNum);h.html(n)}else{jQuery("#in_cart_num").text("0");h.hide().html("");jQuery("#DivShim").css("height","0px").hide();jQuery("#showMiniCart").hide();h.data("inani",false)}}function ajaxDeleteMiniCartItem(o,m,n,k,j,p){var i=URLPrefix.central;i=i+"/cart/ajax.do?action=delete&callback=?";var l;if(m=="deleteWaringItem"){l={deleteWarningQueue:n,rd:Math.random()}}else{if(m=="deletePromote"){l={deleteOverPromotionQueue:n,rd:Math.random()}}else{if(m=="deleteLandingpage"){l={deleteGiftQueue:"landingpage_"+j+"_0_"+k+"_"+n+"_"+p,rd:Math.random()}}else{l={deleteQueue:n,rd:Math.random()}}}}jQuery.getJSON(i,l,function(a){});setTimeout(function(){reloadMiniCart()},2000)}function initMiniCart(){buildCartNumber();if(!jQuery("#miniCart").size()){return}jQuery("#miniCart").data("inani",false).hover(function(b){if(jQuery(this).data("inani")){return
}jQuery(this).data("inani",true);loadMiniCart()},function(c){var d=jQuery(this);jQuery("#DivShim").css("height","0px").hide();jQuery("#showMiniCart").hide();d.data("inani",false)})}jQuery(document).ready(function(){if(isIndex!=1){initMiniCart()}});function initLeftMenu(h){if(!jQuery(".allsort_out_box").size()){return}var j=false;k();setTimeout(g,50);i(h||false);l();function g(){if(j){return}var b=typeof(httpUrl)!="undefined"?httpUrl:"http://www.yihaodian.com";var a=typeof(merchant)!="undefined"?merchant:1;jQuery.getScript(b+"/product/ajaxGetGlobalLeftFloatMenuDataV3.do?callback=ajaxLeftFloatMenuDataV3&merchant="+a)}function k(){var a=jQuery(".allsort_out_box").height();jQuery(".index_side_l").css("paddingTop",a);jQuery(".show_sort").css("min-height",a-2);jQuery(".show_sort").css("height",a-1)}function i(a){if(a){jQuery(".allsort_out_box").show()}else{jQuery(".allsort_out_box").hide();jQuery("#allSortOuterbox").hover(function(){jQuery(this).children(".allsort_out_box").show();jQuery(this).addClass("hover")},function(){jQuery(this).children(".allsort_out_box").hide();
jQuery(this).removeClass("hover")})}}function l(){jQuery(".allsort_out_box ul.allsort li a[tk]").click(function(){var a=jQuery(this),b=a.attr("tk");if(b){addTrackPositionToCookie("1",b)}})}}function ajaxLeftFloatMenuDataV3(c){submenuHtml=true;var d={};jQuery(c.value).find("[category_id]").each(function(){d[jQuery(this).attr("category_id")]=jQuery(this).html()});jQuery(".allsort_out_box ul.allsort li").hover(function(){if(!jQuery(this).data("loaded")){var a=jQuery(this).children(".show_sort");var b=d[a.attr("categoryId")];if(b){a.html(b).find(".show_sort_l dl").hover(function(){jQuery(this).addClass("dl_cur")},function(){jQuery(this).removeClass("dl_cur")}).end().find("a[tk]").each(function(){var f=jQuery(this).attr("tk");if(f){jQuery(this).click(function(){addTrackPositionToCookie("1",f)})}}).end().find(".close_sort").click(function(){a.hide()
});delete d[a.attr("categoryId")]}jQuery(this).data("loaded",true)}jQuery(this).addClass("cur").children(".show_sort").show()},function(){jQuery(this).removeClass("cur").children(".show_sort").hide()})}jQuery(document).ready(function(){if(isIndex!=1){initLeftMenu(false)}});function setAddressCity(d){var c=jQuery.cookie("provinceId");jQuery.cookie("provinceId",d,{domain:no3wUrl,path:"/",expires:800});if(typeof currSiteType!="undefined"&&currSiteType!=1){
	jQuery.ajax({url:URLPrefix.central+"/header/addressChange.do?provinceId="+d+"&timestamp="+new Date()+"&callback=?",dataType:"jsonp",complete:function(){provinceSwitchProvince(d,c)}})}else{provinceSwitchProvince(d,c)}}function provinceSwitchProvince(d,c){jQuery.getJSON(URLPrefix.central+"/header/cartIsEmpty.do?provinceId="+d+"&timestame="+new Date()+"&callback=?",function(b){if("no"==b.value){moveCartItem(d,c)}else{setAddressCityback(b)}var a=URLPrefix.central+"/globalsupport/getOneGrouponAreaIdByProviceId.do?provinceId="+d+"&timestamp="+new Date().getTime()+"&callback=?";jQuery.getJSON(a,function(f){})})}function setAddressCityback(){var s=window.location.href;
if(s.indexOf("merchantID=")!=-1){s=s.substring(0,s.indexOf("merchantID=")-1);window.location.href=s;return}if(s.indexOf("merchant=")!=-1){s=s.substring(0,s.indexOf("merchant=")-1);window.location.href=s;return}if(s.indexOf("/tuangou/")!=-1){if(s.indexOf("/tuangou/myGroupon.do")!=-1){window.location.href=s}return}if(s.indexOf("openProvincePage=")!=-1){s=s.substring(0,s.indexOf("openProvincePage=")-1);window.location.href=s;return}var E=/^\S*product\/\d+_?\d+/;if(s.match(E)){if(s.indexOf("_")!=-1){s=s.substring(0,s.indexOf("_"));window.location.href=s;return}window.location.href=s;return}var q=/^(http:\/\/){0,1}([^\/]+\/)[0-9]+\/[^\/]*$/;if(s.match(q)){s=s.replace(q,"$1$2");window.location.href=s;return}var t=/^(http:\/\/){0,1}[^\/]+\/channel\/[0-9]+_[0-9]+\/$/;if(s.match(t)){s=s.substring(0,s.lastIndexOf("_"));
window.location.href=s;return}var r=/^(http:\/\/){0,1}[^\/]+\/cms\/view.do\?topicId=[0-9]+&merchant=[0-9]+$/;if(s.match(r)){s=s.substring(0,s.lastIndexOf("&merchant"));window.location.href=s;return}var v=/^(http:\/\/){0,1}shop.yihaodian.com\/[^\/^_^\.]+\/[0-9]+\/{0,1}(\?[^\/]+)*$/;if(s.match(v)){window.location.href=s;return}var y=/^(http:\/\/){0,1}www.yihaodian.com\/brand\/[0-9]+\/{0,1}(\?[^\/]+)*$/;if(s.match(y)){window.location.href=s;return}var C=/^(http:\/\/){0,1}[^\/]+\/try\/[0-9]+\/{0,1}(\?[^\/]+)*$/;if(s.match(C)){if(s.lastIndexOf("/")==s.length-1){s=s.substring(0,s.lastIndexOf("/"))}s=s.substring(0,s.lastIndexOf("/"));window.location.href=s;return}var z=/^(http:\/\/){0,1}[^\/]+\/try\/[0-9]+_[0-9]+\/{0,1}(\?[^\/]+)*$/;if(s.match(z)){s=s.substring(0,s.lastIndexOf("_"))+"_0/";window.location.href=s;
return}var D=/^(http:\/\/){0,1}www.yihaodian.com\/S-theme\/[0-9]+\/{0,1}(\?[^\/]+)*$/;if(s.match(D)){window.location.href=s;return}var w=/^(http:\/\/){0,1}www.yihaodian.com\/ctg\/s2\/c([0-9]*)-([^?^\/]*)\/([0-9]*)\/$/;if(s.match(w)){if(s.lastIndexOf("/")==s.length-1){s=s.substring(0,s.lastIndexOf("/"))}s=s.substring(0,s.lastIndexOf("/")+1);window.location.href=s;return}var x=/^(http:\/\/){0,1}search.yihaodian.com\/s2\/c([0-9]*)-([^?^\/]*)\/k([^?^\/]*)\/([0-9]*)\/$/;if(s.match(x)){if(s.lastIndexOf("/")==s.length-1){s=s.substring(0,s.lastIndexOf("/"))}s=s.substring(0,s.lastIndexOf("/")+1);window.location.href=s;return}var F=/^(http:\/\/){0,1}channel\.[^\/]+\/[^\/^_^\.]+(\/[^\/^\.]+){0,1}\/[0-9]+\/{0,1}(\?[^\/]+){0,1}(#[^\/]+)*$/;if(s.match(F)){if(s.indexOf("#")!=-1){s=s.substring(0,s.indexOf("#"))
}if(s.indexOf("?")!=-1){var B=s.substring(s.indexOf("?"));var A=s.substring(0,s.indexOf("?"));if(s.lastIndexOf("/")==s.length-1){A=A.substring(0,A.lastIndexOf("/"));B="/"+B}A=A.substring(0,A.lastIndexOf("/"));s=A+B}else{if(s.lastIndexOf("/")==s.length-1){s=s.substring(0,s.lastIndexOf("/"))}s=s.substring(0,s.lastIndexOf("/"))}window.location.href=s;return}if(s.indexOf("confirmOrder")!=-1&&s.indexOf("saveOrder")!=-1){window.location.href=URLPrefix.central;return}var u=URLPrefix.search+"/s/";if(s.substr(0,u.length)==u){var E=/-p\d{0,3}/;if(s.match(E)){s=s.replace(E,"-p1");window.location.href=s;return}}window.location.reload()}function moveCartItem(c,d){jQuery.getJSON(URLPrefix.central+"/cart/globalMoveCartItem.do?provinceId="+c+((d)?"&oldProvinceId="+d:"")+"&timestamp="+new Date().getTime()+"&callback=?",function(a){setAddressCityback()
})}function initProvince(){YHDGLOBAL.getCookie("provinceId",function(f){var e=this["provinceId"];if(e&&e>0){jQuery("#currProvince").text(jQuery("#p_"+e).text()+"站");var d=jQuery("#weibo");if(e==2){d.attr("href","http://weibo.com/yihaodianbeijing")}else{if(e==20){d.attr("href","http://weibo.com/yihaodianguangzhou")}else{d.attr("href","http://weibo.com/yihaodian")}}}else{showProvinces()}})}function closeProvinces(c){if(c<=0){c=1}var d=jQuery("#currProvince").text();if(d==""){setAddressCity(c)}else{jQuery("#allProvinces").hide()}}function showProvinces(){var b=URLPrefix.central+"/header/selectProvincebox.do?timestamp="+new Date().getTime()+"&callback=?";jQuery.getJSON(b,function(a){if(!a.ERROR&&a.value){jQuery("#provinceboxDiv").html(a.value);jQuery("#allProvinces").jqm({overlay:50,closeClass:"jqmClose",trigger:".jqModal",overlayClass:"pop_win_bg",modal:true,toTop:true}).jqmShow().jqmAddClose(".popwinClose")
}jQuery.getJSON(URLPrefix.central+"/header/cartIsEmpty.do?callback=?",function(d){if("no"==d.value){jQuery("#provincesPoptips").show()}else{jQuery("#provincesPoptips").hide()}})})}jQuery(document).ready(function(){if(isIndex!=1){initProvince()}});jQuery(document).ready(function(){if(isIndex!=1){}});function initPrompt(){jQuery("#changeReceiverGoodsCity").click(function(){var c=jQuery("#buyPromptDiv");var a=parseInt(c.css("top").replace("px",""))+75;var b=parseInt(c.css("left").replace("px",""))+225;jQuery("#receiverGoodsCityDiv").css("top",a+"px");jQuery("#receiverGoodsCityDiv").css("left",b+"px");jQuery(window).scroll(function(){var e=parseInt(c.css("top").replace("px",""))+75;var d=parseInt(c.css("left").replace("px",""))+225;jQuery("#receiverGoodsCityDiv").css("top",e+"px");jQuery("#receiverGoodsCityDiv").css("left",d+"px")});jQuery("#receiverGoodsCityDiv").show()});jQuery("#closeReceiverGoodsCity").click(function(){jQuery("#receiverGoodsCityDiv").hide()});jQuery("#validate").click(function(){jQuery("#buyPromptDiv").hide();setPromptCookie();
jQuery("#receiverGoodsCityDiv").hide()})}function changeReceiverGoodsCity(id){jQuery("#selectCity").val(id);if(jQuery("#p_"+id)){var city=jQuery("#p_"+id).find("a").text()}jQuery("#selectCity").text(city);jQuery("#cityId").attr("value",id);jQuery("#message").text("");jQuery("#receiverGoodsCityDiv").hide();var productId=jQuery("#validateProductId").val();var num=typeof(jQuery("#validateQty").val())=="undefined"?1:jQuery("#validateQty").val();var param="productID="+productId+"&productNum="+num+"&provinceId="+id;var url=URLPrefix.central+"/product/validateProductInProvince.do?productID="+productId+"&productNum="+num+"&provinceId="+id+"&callback=?";jQuery.getJSON(url,function(data){if(data.ERROR){}else{try{html=eval("("+data.value+")")}catch(e){jQuery("#message").text("系统出错")}if(html.result=="success"){var provinceId=jQuery.cookie("provinceId");
if(id!=provinceId){jQuery("#validate").unbind("click");jQuery("#validate").bind("click",function(){setPromptCookie();setAddressCity(id)})}else{}}else{var value=html["key_"+productId];if(value){jQuery("#message").text(value)}jQuery("#validate").bind("click",function(){jQuery("#buyPromptDiv").hide()})}}})}function setPromptCookie(){var c;if(typeof(promptExpireTime)=="undefined"&&!jQuery("#promptExpireTime")){c=10}else{c=jQuery("#promptExpireTime").val()}c=c?c:10;var b=new Date();var a=b.getTime()+c*24*1000*3600;b.setTime(a);document.cookie="prompt_flag=1;path=/;domain=."+no3wUrl+";expires="+b.toGMTString()};var suggestLength=0;var curSuggestIndex=-1;function findNames(l,j,k){var h="0";if(jQuery("#leaf").size()>0){h=jQuery("#leaf").val()}var i=j.keyCode;if(jQuery(l).val()!=k&&i!="38"&&i!="40"){var g=URLPrefix.search+"/get_keywords.do?keyword="+encodeURIComponent(encodeURIComponent(jQuery(l).val()))+"&leaf="+h+"&callback=?";jQuery.getJSON(g,function(a){if(a.ERROR){}else{var b=false;jQuery("#searchSuggest").html(a.value).find("a").each(function(){var c=jQuery(this).find("span").html();if(c){jQuery(this).html(c);jQuery(this).addClass(b?"odd":"even");b=!b;if(jQuery("#searchSuggest ul li").size()>0){jQuery("#searchSuggest ul li").hover(function(){jQuery(this).addClass("select").siblings().removeClass("select")},function(){jQuery(this).removeClass("select")})}}});loadComplete_findNames()}})}}function loadComplete_findNames(){suggestLength=jQuery("#searchSuggest li").length;
curSuggestIndex=-1;jQuery("#searchSuggest").show()}function searchMe(n,l,m){var i=null;var o=document.getElementById("recommendId");if(o){i=o.value}var k=null;var p=document.getElementById("recommendName");if(p){k=p.value}if(!n){n=jQuery("#keyword").val()}if(n!=null&&n!=""){addKeywordHistory(n)}n=n.replace(/\//gi," ");var j="0";if(jQuery("#leaf").size()>0){j=jQuery("#leaf").val()}if(l!=null&&l!="0"){window.location=URLPrefix.search_keyword+"/s2/c"+l+"-"+m+"/k"+encodeURIComponent(encodeURIComponent(n))+"/"}else{if(i!=null&&i!=""){window.location=URLPrefix.search_keyword+"/s2/c"+i+"-"+k+"/k"+encodeURIComponent(encodeURIComponent(n))+"/"}else{window.location=URLPrefix.search_keyword+"/s2/c"+j+"-0/k"+encodeURIComponent(encodeURIComponent(n))+"/"}}}function addKeywordHistory(b){if(typeof(b)=="undefined"){return
}b=jQuery.trim(b);b=b.replace(/[,]/g," ");b=b.replace(/[，]/g," ")}function roll(l){l=l||window.event;var k=l.keyCode;suggestLength=jQuery("#searchSuggest li").length;var g="";var i="";if(jQuery("#searchSuggest ul li").size()>0){if(k=="38"){jQuery("#searchSuggest ul li").removeClass("select");if(curSuggestIndex<=0){curSuggestIndex=suggestLength-1}else{if(curSuggestIndex==1){curSuggestIndex=0}else{curSuggestIndex=curSuggestIndex-1}}jQuery("#searchSuggest ul li").eq(curSuggestIndex).addClass("select")}else{if(k=="40"){jQuery("#searchSuggest ul li").removeClass("select");if(curSuggestIndex==0){curSuggestIndex=1}else{if(curSuggestIndex>=(suggestLength-1)){curSuggestIndex=0}else{curSuggestIndex=curSuggestIndex+1}}jQuery("#searchSuggest ul li").eq(curSuggestIndex).addClass("select")}}if(k=="38"||k=="40"){var j=jQuery("#searchSuggest  ul li a").eq(curSuggestIndex).text();
if(jQuery("#searchSuggest ul li").eq(curSuggestIndex).attr("id")!=null&&jQuery("#searchSuggest ul li").eq(curSuggestIndex).attr("id")!=""){var h=jQuery("#searchSuggest  ul li a").eq(0).text();jQuery("#keyword").val(h);if(jQuery("#searchSuggest ul li ").eq(curSuggestIndex).attr("id")=="recom1"){g=document.getElementById("recom1Id").value;i=document.getElementById("recom1Name").value}if(jQuery("#searchSuggest ul li").eq(curSuggestIndex).attr("id")=="recom2"){g=document.getElementById("recom2Id").value;i=document.getElementById("recom2Name").value}}else{jQuery("#keyword").val(j)}document.getElementById("recommendId").value=g;document.getElementById("recommendName").value=i}}if(k=="13"){if(g!=""){searchMe(jQuery("#keyword").val(),g,i)}else{searchMe(jQuery("#keyword").val(),"0","0")}}}function selectSearchCategory(c,d){jQuery("#searchCategory").html(d);
jQuery("#leaf").val("0_"+c)}function emptySearchBar(){if(jQuery("#keyword").data("default")&&!jQuery("#keyword").val().indexOf(jQuery("#keyword").data("default"))){jQuery("#keyword").val(jQuery("#keyword").val().substring(jQuery("#keyword").data("default").length));jQuery("#keyword").trigger("blur")}}function hotKeywords_onDocumentReady(){var divHotkeywordsList=jQuery("#hotKeywordsList")[0];if(!divHotkeywordsList){return}var hotkeywordsList=divHotkeywordsList.innerHTML;eval("var hots = "+hotkeywordsList);var hour=new Date().getHours();var def=new Array();if(hots[hour].length>0){def.push(hots[hour][0])}def.push(hots[24][0]);if(def.length>0){var keywords=def[0];var oldKeywords=jQuery("#keyword").val();if(oldKeywords==""){jQuery("#keyword").attr("value",keywords);jQuery("#keyword").data("default",keywords)
}if(oldKeywords==keywords||oldKeywords==""){jQuery("#keyword").css("color","#999999");jQuery("#keyword").bind("focus",function(){if(this.value==keywords){this.value="";this.style.color="#333333"}}).bind("blur",function(){if(this.value==""){this.value=keywords;this.style.color="#999999"}})}}var curr=new Array();for(var i=1;i<hots[hour].length;i++){curr.push(hots[hour][i])}for(var t=0;t<100;t++){var i=Math.floor(Math.random()*hots[24].length);var word=hots[24][i];var contains=false;for(var j=0;j<curr.length;j++){if(word==curr[j]){contains=true;break}}if(!contains&&word!=keywords){curr.push(word)}}if(curr.length>0){var buf="";for(var i=0;i<curr.length;i++){var show="";if(curr[i]&&curr[i]!="undefined"){show=curr[i]}buf=buf+"<a href='"+URLPrefix.search_keyword+"/s2/c0-0/k"+encodeURIComponent(show)+"/' title='搜索:"+show+"'>"+show+"</a>"
}jQuery("#hotKeywordsShow").html(buf);bindLinkClickTracker("hotKeywordsShow","HotKeyword")}}jQuery(document).ready(function(){if(isIndex!=1){hotKeywords_onDocumentReady()}});(function(b){b.fn.bgIframe=b.fn.bgiframe=function(f){if(b.browser.msie&&parseInt(b.browser.version)<=6){f=b.extend({top:"auto",left:"auto",width:"auto",height:"auto",opacity:true,src:"javascript:false;"},f||{});var e=function(c){return c&&c.constructor==Number?c+"px":c},a='<iframe class="bgiframe"frameborder="0"tabindex="-1"src="'+f.src+'"style="display:block;position:absolute;z-index:-1;'+(f.opacity!==false?"filter:Alpha(Opacity='0');":"")+"top:"+(f.top=="auto"?"expression(((parseInt(this.parentNode.currentStyle.borderTopWidth)||0)*-1)+'px')":e(f.top))+";left:"+(f.left=="auto"?"expression(((parseInt(this.parentNode.currentStyle.borderLeftWidth)||0)*-1)+'px')":e(f.left))+";width:"+(f.width=="auto"?"expression(this.parentNode.offsetWidth+'px')":e(f.width))+";height:"+(f.height=="auto"?"expression(this.parentNode.offsetHeight+'px')":e(f.height))+';"/>';
return this.each(function(){if(b("> iframe.bgiframe",this).length==0){this.insertBefore(document.createElement(a),this.firstChild)}})}return this};if(!b.browser.version){b.browser.version=navigator.userAgent.toLowerCase().match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/)[1]}})(jQuery);var _indexUrl=URLPrefix.central;var _my=URLPrefix.my;if(typeof currSiteType!="undefined"&&currSiteType==2){_indexUrl=URLPrefix.mall;_my=URLPrefix.mymall}function executiveLandedCheck(){var b=jQuery.cookie("ut");var a=0;if(b){a=1}if(a==0){yhdPublicLogin.showLoginDivNone(URLPrefix.passport,false,"",function(c){if(c==0){yhdPublicLogin.showTopLoginInfo()}});return}}function submitFavorite(c,a){executiveLandedCheck();var b=URLPrefix.my+"/member/myCollection/addFavorite.do?tag.productID="+c+"&tag.merchantID="+a+"&callback=?";jQuery.getJSON(b,function(d){if(d.ERROR){}else{showFavoriteResult(d)}})}function showFavoriteResult(b){var a=b.code;if(a==0){var d=0;var c=0;var e=URLPrefix.my+"/member/myCollection/getPopboxInfo.do?callback=?";jQuery.getJSON(e,function(g){if(g.ERROR){}else{YHD.popwin(g.value,460,349);
var f="";jQuery(".fkey li a").click(function(){var h=jQuery(this).attr("flag");if(h=="false"){jQuery(this).addClass("on");f=f+" "+jQuery(this).text();jQuery("#tagName").attr("value",jQuery.trim(f));jQuery(this).attr("flag","true")}else{jQuery(this).removeClass("on");jQuery(this).attr("flag","false");var j="";var k=jQuery.trim(jQuery("#tagName").attr("value")).split(" ");for(var i=0;i<k.length;i++){if(k[i]!=jQuery(this).text()){j=j+" "+k[i]}}jQuery("#tagName").attr("value",jQuery.trim(j));f=jQuery("#tagName").attr("value")}});if(jQuery("#checkFlag").checked){c=1}jQuery("#addTag_confirm").click(function(){submitpTag(b.productID,b.merchantID,c)})}})}else{if(a==1){YHD.alert("您已经收藏过该商品！")}else{if(a==3){YHD.alert("添加收藏失败！")}else{if(a==4){YHD.alert("包含非法词汇，操作无法完成！")}else{if(a==5){window.location.href=_indexUrl+"/passport/login_input.do?returnUrl="+encodeURIComponent(window.location.href)
}else{if(a==6){window.location.href=URLPrefix.central+"/product/cart.do?action=view"}}}}}}}function submitpTag(b,d,c){jQuery(".popwinClose").click();var a=jQuery("#tagName").val();if(a==""){return}if(jQuery.trim(a)!=""){var f=a.split(" ");if(f.length>3){YHD.alert("每次最多输入三个标签");return}else{for(var g=0;g<f.length;g++){if(f[g].length>10){YHD.alert("标签名字过长，每个标签不能超过10个汉字或字符");return}}}if(a.length>33){YHD.alert("标签名字过长，每个标签不能超过10个汉字或字符");return}}var e=URLPrefix.my+"/member/myCollection/addProductTag.do?tag.productID="+b+"&tag.merchantID="+d+"&tag.name="+encodeURIComponent(encodeURIComponent(jQuery.trim(a)))+"&tag.checkFlag="+c+"&callback=?";jQuery.getJSON(e,function(h){if(h.ERROR){}else{showEditFavoriteResult(h)}})}function showEditFavoriteResult(a){if(a.code==5){jQuery(".popwinClose").click();YHD.alert("标签已经添加过该商品")
}else{if(a.code==0){if(jQuery.trim(jQuery("#collectionProductAll").html())!=""){window.location=_my+"/member/myCollection/myCollectionProduct.do"}else{jQuery(".popwinClose").click();YHD.alert("该商品标签已添加成功！")}}}};var myProductId="";var myMerchantId="";var myOrderId="";function appearExperience(i,h,f){var g=jQuery.cookie("ut");if(!g){window.location.href="/passport/login_input.do?returnUrl="+window.location.href;return}else{executiveLandedCheck();myProductId=i;myOrderId=f;if(h!=null){myMerchantId=h}var j=URLPrefix.central+"/product/checkItemCount.do?productID="+i+"&callback=?";jQuery.getJSON(j,function(a){if(a.ERROR){}else{apperarSuccess(a,i)}})}}function apperarSuccess(f,g){if(f.value==-1){YHD.alert("您已经发表过商品评论，不能再次发表.",null,476,27);return}if(f.value==0){var a=jQuery("#ex_prompt");YHD.popwin(a.html(),466,283);return}var h=URLPrefix.my+"/member/exp/showProductExcerienceDiv.do?productId="+g+"&callback=?";jQuery.getJSON(h,function(b){if(b.ERROR){}else{if((navigator.userAgent.indexOf("MSIE")>=0)&&(navigator.userAgent.indexOf("Opera")<0)){YHD.popwin(b.value,460,415)
}else{YHD.popwin(b.value,460,439)}jQuery(".popbox").bgiframe();new Stars("stars1");jQuery("[name=experience.submit]").click(function(){deployExperience(myProductId,myMerchantId)})}})}function deployExperience(n,s){var r=/<[^> ]+>/;var v=URLPrefix.central+"/product/deployExperience.do?productID="+shareProductId+"&merchantID="+shareMerchantId+"&action=create";var p=jQuery("#contentTitle").val();if(p&&""!=jQuery.trim(p)){p=p.replace(/(^\s*)|(\s*$)/g,"");if(p.length>15){showErrorTitle("errorTitle","请将您输入的标题字数超出15个字的字数限制!");return}if(r.exec(p)){showErrorTitle("errorTitle","您输入的格式有误!");return}}else{showErrorTitle("errorTitle","请您填写评论的标题!");this.focus();return}v=v+"&title="+encodeURIComponent(encodeURIComponent(p));var l=true;jQuery(".ipt3").each(function(d){var a=jQuery(this).attr("nullTitile");var e=jQuery(this).attr("categoryTitile");
var b=jQuery(this).attr("experienceTitle");var c=jQuery(this).val().replace(/(^\s*)|(\s*$)/g,"");if(c==""){showErrorTitle(a,"请写写这个商品的"+a+"!");this.focus();l=false;return false}if(c.length>200){showErrorTitle(a,"请将您输入的"+a+"字数超出200个字的字数限制!");l=false;return false}if(r.exec(c)){showErrorTitle(a,a+"您输入的格式有误!");l=false;return false}v=v+"&"+e+"="+encodeURIComponent(encodeURIComponent(c))+"&"+b+"="+encodeURIComponent(encodeURIComponent(a))});if(l){var u=jQuery("#stars1-input");var o="5";if(u.val()!=""&&u.val()!=null){o=u.val()}var q="5";var m="5";var t=jQuery("#u_realName").text();if(t&&""!=jQuery.trim(t)){t=t.replace(/(^\s*)|(\s*$)/g,"")}else{t=jQuery("#userRealName").val();if(t&&""!=jQuery.trim(t)){t=t.replace(/(^\s*)|(\s*$)/g,"")}else{showErrorTitle("realNameError","请填写您的昵称！");return}}jQuery("[name=experience.submit]").css("disabled",true);
v=v+"&qualityRating="+o+"&deliveryRating="+q+"&serviceRating="+m+"&userRealName="+encodeURIComponent(encodeURIComponent(t))+"&callback=?";jQuery.getJSON(v,function(a){if(a.ERROR){}else{showShareDeployPage(a)}});return true}else{return false}}function showDeployPage(e){var h=e.value.split("=");var f=h[0];var g=h[1];if(f==1){jQuery(".popwinClose").click();YHD.alert(g,null,476,27)}else{YHD.alert(g,null,476,27)}if(jQuery.trim(jQuery("#categoryAllProducts").html())!=""){loadExpInfo(1)}};var shareProductId="";var shareMerchantId="";var shareOrderId="";var shareContentgood="";var shareImage_url="";var productname="";function apperarShareSuccess(f,g){if(f.value==-1){yhdPublicLogin.showLoginDivNone(URLPrefix.passport,false,"",function(b){if(b==0){yhdPublicLogin.showTopLoginInfo()}});return}if(f.value==0){var a=jQuery("#ex_prompt");YHD.popwin(a.html(),466,283);return}var h=URLPrefix.my+"/member/exp/showProductExcerienceDiv.do?productId="+g+"&merchantId="+shareMerchantId+"&soId="+shareOrderId+"&callback=?";jQuery.getJSON(h,function(c){if(c.value==null||c.value==""||c.ERROR){}else{YHD.popwin(c.value);YHD.popwin(c.value,jQuery(".popbox").width(),jQuery(".popbox").height());jQuery(".popbox").bgiframe();new Stars("stars1");var b=jQuery("#experience_info").attr("soid");if(jQuery("#stars_desc").size()>0&&jQuery("#stars_atti").size()>0&&jQuery("#stars_logi").size()>0){new Stars("stars_desc");
new Stars("stars_atti");new Stars("stars_logi")}shareMerchantId=jQuery("#experience_info").attr("merchantid");jQuery("[name=experience.submit]").click(function(){shareMerchantId=jQuery("#experience_info").attr("merchantid");deployShareExperience(shareProductId,shareMerchantId,b)})}})}function deployShareExperience(p,x,w,s){shareProductId=p;shareMerchantId=x;var v=/<[^> ]+>/;var z=URLPrefix.central+"/product/deployExperience.do?productID="+p+"&merchantID="+x+"&action=create&soId="+w;var r=jQuery("#contentTitle").val();if(r&&""!=jQuery.trim(r)){r=r.replace(/(^\s*)|(\s*$)/g,"");if(r.length>15){jQuery("span[id='titleDesc']").html("<em class='r'>*超出15个字的字数限制</em>");return}if(r.length<5){jQuery("span[id='titleDesc']").html("<em class='r'>*内容不少于5个字</em>");return}if(v.exec(r)){jQuery("span[id='titleDesc']").html("<em class='r'>*请填写评论标题(5-15个字)</em>");
return}}else{jQuery("span[id='titleDesc']").html("<em class='r'>*请填写评论标题(5-15个字)</em>");return}jQuery("span[id='titleDesc']").html("<em class='r'>*</em>请填写评论标题(5-15个字)");z=z+"&title="+encodeURIComponent(encodeURIComponent(r));var n=true;var t="#yhd_pop_win .ipt3,#yhd_pop_win .ipt6";if(s&&s=="publishExperience"){t="#publishExperience .ipt3,#publishExperience .ipt6"}jQuery(t).each(function(b){var d=jQuery(this).attr("nullTitile");var c=jQuery(this).attr("categoryTitile");var e=jQuery(this).attr("experienceTitle");var a=jQuery(this).val().replace(/(^\s*)|(\s*$)/g,"");if(a==""){if(c=="contentGood"){jQuery("span[id='contentTitle1Desc']").html("<em class='r'>*请填写评论内容(5-200字)</em>");n=false;return false}jQuery("span[id='contentTitle1Desc']").html("<em class='r'>*</em>请填写评论内容(5-200字)");if(c=="contentFail"){jQuery("span[id='contentTitle2Desc']").html("<em class='r'>*请填写评论内容(5-200字)</em>");
n=false;return false}jQuery("span[id='contentTitle2Desc']").html("<em class='r'>*</em>请填写评论内容(5-200字)");if(c=="content"){jQuery("span[id='contentTitle3Desc']").html("<em class='r'>*请填写评论内容(5-200字)</em>");n=false;return false}jQuery("span[id='contentTitle3Desc']").html("<em class='r'>*</em>请填写评论内容(5-200字)")}if(a.length>200){if(c=="contentGood"){jQuery("span[id='contentTitle1Desc']").html("<em class='r'>*超出200个字的字数限制</em>");n=false;return false}jQuery("span[id='contentTitle1Desc']").html("<em class='r'>*</em>请填写评论内容(5-200字)");if(c=="contentFail"){jQuery("span[id='contentTitle2Desc']").html("<em class='r'>*超出200个字的字数限制</em>");n=false;return false}jQuery("span[id='contentTitle2Desc']").html("<em class='r'>*</em>请填写评论内容(5-200字)");if(c=="content"){jQuery("span[id='contentTitle3Desc']").html("<em class='r'>*超出200个字的字数限制</em>");
n=false;return false}jQuery("span[id='contentTitle3Desc']").html("<em class='r'>*</em>请填写评论内容(5-200字)")}if(a.length<5){if(c=="contentGood"){jQuery("span[id='contentTitle1Desc']").html("<em class='r'>*内容不少于5个字</em>");n=false;return false}jQuery("span[id='contentTitle1Desc']").html("<em class='r'>*</em>请填写评论内容(5-200字)");if(c=="contentFail"){jQuery("span[id='contentTitle2Desc']").html("<em class='r'>*内容不少于5个字</em>");n=false;return false}jQuery("span[id='contentTitle2Desc']").html("<em class='r'>*</em>请填写评论内容(5-200字)");if(c=="content"){jQuery("span[id='contentTitle3Desc']").html("<em class='r'>*内容不少于5个字</em>");n=false;return false}jQuery("span[id='contentTitle3Desc']").html("<em class='r'>*</em>请填写评论内容(5-200字)")}if(v.exec(a)){if(c=="contentGood"){jQuery("span[id='contentTitle1Desc']").html("<em class='r'>*</em>请填写评论内容(5-200个字)");
n=false;return false}jQuery("span[id='contentTitle1Desc']").html("<em class='r'>*</em>请填写评论内容(5-200字)");if(c=="contentFail"){jQuery("span[id='contentTitle2Desc']").html("<em class='r'>*</em>请填写评论内容(5-200个字)");n=false;return false}jQuery("span[id='contentTitle2Desc']").html("<em class='r'>*</em>请填写评论内容(5-200字)");if(c=="content"){jQuery("span[id='contentTitle3Desc']").html("<em class='r'>*</em>请填写评论内容(5-200个字)");n=false;return false}jQuery("span[id='contentTitle3Desc']").html("<em class='r'>*</em>请填写评论内容(5-200字)")}z=z+"&"+c+"="+encodeURIComponent(encodeURIComponent(a))+"&"+e+"="+encodeURIComponent(encodeURIComponent(d))});if(n){var q=getStartvalue("#stars1-input");var u=5;var o=5;var y=5;if(jQuery("#stars_logi-input").size()>0&&jQuery("#stars_atti-input").size()>0&&jQuery("#stars_desc-input").size()>0){u=getStartvalue("#stars_logi-input");
o=getStartvalue("#stars_atti-input");y=getStartvalue("#stars_desc-input")}jQuery("[name=experience.submit]").css("disabled",true);z=z+"&qualityRating="+q+"&deliveryRating=5&serviceRating=5&mdeliveryPoint="+u+"&mservicePoint="+o+"&mdescriptPoint="+y+"&callback=?";jQuery(".evaBtn").attr("disabled","disabled");jQuery.getJSON(z,function(a){jQuery(".evaBtn").removeAttr("disabled");if(a.ERROR){}else{jQuery(".popwinClose").click();showShareDeployPage(a,s)}});return true}else{return false}}function getStartvalue(f){var e=jQuery(f);var d="5";if(e&&e.val()!=""&&e.val()!=null){d=e.val()}return d}function showErrorTitle(c,d){jQuery("#"+c).css({color:"#666",display:"inline-block",height:"23px","line-height":"23px",padding:"0 5px",border:"1px solid #FF8080","background-color":"#FFF2F2"});jQuery("#"+c).html(d);
setTimeout(function(){jQuery("#"+c).removeAttr("style");jQuery("#"+c).html("")},9000)}function showShareDeployPage(g,k){var l=g.value.split("=");var h=l[0];var i=l[1];if(h==1){jQuery(".popwinClose").click();window.scrollTo(0,0);var j='<div class="popup_cont"><div class="prompted"><img src="'+URLPrefix.statics+'/member/images/okay.png"/><h3>提交成功！ </h3><p>'+i+'!</p></div><p class="countdown">3秒后自动关闭</p><a href="javascript:void(0);" class="close">关闭</a></div>';popMsg(j,".popup_cont .close",378,120);if(jQuery("#categoryAllProducts")&&jQuery("#categoryAllProducts").size()>0){loadExpInfo(1)}if(jQuery("#noCommentProductsView")&&jQuery("#noCommentProductsView").size()>0){loadNoCommentProductInfo()}if(jQuery("#myOrderList")&&jQuery("#myOrderList").size()>0){orderListPageInfo(1)}setTimeout(function(){jQuery(".popup_cont .close").click();
openShare();if(k&&k=="publishExperience"){jQuery("#contentTitle").attr("value","");jQuery(".ipt3, .ipt6").each(function(a){jQuery(this).attr("value","")})}},3000)}else{if(h==2){var j='<div class="popup_sens"><h3 class="title">提示<span class="close">关闭</span></h3><p class="popup_text">'+i+'</p><p class="btntip"><input type="button" value="确 定" /></p></div>';popMsg(j,".popup_sens .close,.btntip :button",398,150,250);jQuery(".popup_sens .close,.btntip :button").click(function(){openShare()});if(jQuery("#categoryAllProducts")&&jQuery("#categoryAllProducts").size()>0){loadExpInfo(1)}if(jQuery("#noCommentProductsView")&&jQuery("#noCommentProductsView").size()>0){loadNoCommentProductInfo()}if(jQuery("#myOrderList")&&jQuery("#myOrderList").size()>0){orderListPageInfo(1)}if(k&&k=="publishExperience"){jQuery("#contentTitle").attr("value","");
jQuery(".ipt3, .ipt6").each(function(a){jQuery(this).attr("value","")})}}else{var j='<div class="popup_sens"><h3 class="title">提示<span class="close">关闭</span></h3><p class="popup_text">'+i+'</p><p class="btntip"><input type="button" value="确 定" /></p></div>';popMsg(j,".popup_sens .close,.btntip :button",398,150);if(k&&k=="publishExperience"){jQuery("#contentTitle").attr("value","");jQuery(".ipt3, .ipt6").each(function(a){jQuery(this).attr("value","")})}}}}function popMsg(l,i,k,g,j){YHD.init();var h=jQuery("#yhd_pop_win");if(l!=null){jQuery(h).html(l)}if(j){YHD.initPosition(h,k,g,j)}else{YHD.initPosition(h,k,g)}jQuery(h).jqm({overlay:10,overlayClass:"pop_win_bg",modal:true,toTop:true}).jqmShow().jqmAddClose(i)}function appearAndShareExperience(l,k,h,j,m){productname=m;var i=jQuery.cookie("ut");
if(!i){yhdPublicLogin.showLoginDivNone(URLPrefix.passport,false,"",function(a){if(a==0){yhdPublicLogin.showTopLoginInfo()}});return}else{executiveLandedCheck();shareImage_url=j;shareProductId=l;if(k!=null&&k!=0&&h!=null&&h!=0){shareOrderId=h;shareMerchantId=k}else{h=null;k=null}var n=URLPrefix.central+"/product/checkItemCount.do?productID="+l+"&merchantId="+shareMerchantId+"&soId="+shareOrderId+"&callback=?";jQuery.getJSON(n,function(a){if(a.ERROR){}else{apperarShareSuccess(a,l)}})}}function openShare(){jQuery("#pop_win_ok_btn").click();var b=URLPrefix.my+"/member/exp/editShareExcerienceDiv.do?productId="+shareProductId+"&callback=?";jQuery.getJSON(b,function(a){if(a.ERROR){}else{if(a.value!="noshare"){getSinaUrl(function(f){YHD.popwin(a.value,470,400);jQuery("[name=experience.cancel]").click(function(){jQuery(".popwinClose").click()
});if(shareImage_url==""||productname==""||shareImage_url==null||productname==null){try{shareImage_url=jQuery("#mainPic").attr("tag");productname=jQuery("#productMainName").text()}catch(e){}}jQuery("[name='product_image']").attr("src",shareImage_url);shareContentgood="我在1号店买了【"+productname.substring(0,110)+"】 ";jQuery("[name='product_share_experience']").val(shareContentgood+f);jQuery("[name=experience.share]").click(function(){if(jQuery("[name='product_share_experience']").val().length>140){jQuery("#shareErrorMsg").html("<p>分享内容不能超过140字</p>")}else{if(jQuery("[name='product_share_experience']").val().length==0){jQuery("#shareErrorMsg").html("<p>分享内容不能为空</p>")}else{var c=false;jQuery(":checkbox[name^='share_to_']").each(function(d){c=c||jQuery(this).attr("checked")});if(c){perpShareExcerience(shareProductId)
}else{jQuery("#shareErrorMsg").html("<p>请选择分享平台</p>")}}}})})}}})}var sinaUrl="";function getSinaUrl(b){if(sinaUrl!=""){b(sinaUrl)}else{getShotURL("sina",b)}}function getShotURL(d,c){if(d=="sina"&&sinaUrl!=""){c(sinaUrl)}else{url=URLPrefix.passport+"/share/shotUrl.do?longUrl="+escape(URLPrefix.central+"/product/"+shareProductId+"?tarcker_u="+getpcode(d))+"&callback=?";jQuery.getJSON(url,function(a){c(a.shotUrl);if(d=="sina"){sinaUrl=a.shotUrl}})}}function getpcode(b){if("sina"==b){return"1047513694"}if("qq"==b){return"1057943695"}if("kaixin"==b){return"1095733696"}if("renren"==b){return"1020853697"}}var shareMsg="";var sig="";var exid="";function perpShareExcerience(b){shareMsg=encodeURIComponent(encodeURIComponent(jQuery("[name='product_share_experience']").val()));sig=jQuery("#shareErrorMsg").attr("sign");
exid=jQuery("#shareErrorMsg").attr("exid");validateShare(b,"sina");validateShare(b,"renren");validateShare(b,"qq");validateShare(b,"kaixin");jQuery(".popwinClose").click();YHD.alert("评论并且分享成功",null,476,27)}function validateShare(d,c){if(jQuery(":checkbox[name='share_to_"+c+"']")&&jQuery(":checkbox[name='share_to_"+c+"']").attr("checked")){shareExcerience(d,c)}}function createShareUrl(h,g,f){var e=URLPrefix.passport+"/share/"+g+"/publish.do?sign="+sig+"&exid="+exid+"&productId="+shareProductId+"&pubMessage.content="+shareMsg+"&pubMessage.title="+encodeURIComponent(encodeURIComponent("在一号店发布了购买体验"))+"&pubMessage.image="+escape(shareImage_url)+"&pubMessage.url="+escape(f)+"&sinaUrl="+escape(sinaUrl)+"&callback=?";return e}function shareExcerience(d,c){getShotURL(c,function(a){jQuery.getJSON(createShareUrl(d,c,a),function(b){})
})};var Class={create:function(){return function(){this.initialize.apply(this,arguments)}}};var Extend=function(b,a){for(var c in a){b[c]=a[c]}};function stopDefault(a){if(a&&a.preventDefault){a.preventDefault()}else{window.event.returnValue=false}return false}var Stars=Class.create();Stars.prototype={initialize:function(j,e){this.SetOptions(e);var m=999;var g=(document.all)?true:false;var o=document.getElementById(j).getElementsByTagName("a");var a=document.getElementById(this.options.Input)||document.getElementById(j+"-input");var l=document.getElementById(this.options.Tips)||document.getElementById(j+"-tips");var f=" "+this.options.nowClass;var d=this.options.tipsTxt;var k=o.length;for(h=0;h<k;h++){o[h].value=h;o[h].onclick=function(c){stopDefault(c);this.className=this.className+f;m=this.value;
a.value=this.getAttribute("star:value");l.innerHTML=d[this.value]};o[h].onmouseover=function(){if(m<999){var c=RegExp(f,"g");o[m].className=o[m].className.replace(c,"")}};o[h].onmouseout=function(){if(m<999){o[m].className=o[m].className+f}}}if(g){var b=document.getElementById(j).getElementsByTagName("li");for(var h=0,k=b.length;h<k;h++){var n=b[h];if(n){n.className=n.getElementsByTagName("a")[0].className}}}},SetOptions:function(a){this.options={Input:"",Tips:"",nowClass:"current-rating",tipsTxt:["1分-很不满意","2分-不满意","3分-一般","4分-满意","5分-非常满意"]};Extend(this.options,a||{})}};function setHomepage(){if(document.all){document.body.style.behavior="url(#default#homepage)";document.body.setHomePage(httpUrl)}else{if(window.sidebar){if(window.netscape){try{netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect")}catch(c){alert("该操作被浏览器拒绝，如果想启用该功能，请在地址栏内输入 about:config,然后将项 signed.applets.codebase_principal_support 值该为true")}}var d=Components.classes["@mozilla.org/preferences-service;1"].getService(Components.interfaces.nsIPrefBranch);d.setCharPref("browser.startup.homepage",httpUrl)}}}function globalLogoff(){}function bookmark(){if(document.all){window.external.AddFavorite(httpUrl,favorite)}else{try{window.sidebar.addPanel(favorite,httpUrl,"")}catch(b){alert("抱歉，您所使用的浏览器无法完成此操作。\n\n加入收藏失败，请使用Ctrl+D进行添加")}}}var myCartTopHeaderTimer;function clearMyCartTopHeaderTimer(){if(myCartTopHeaderTimer!=null){clearTimeout(myCartTopHeaderTimer)
}}function buildMyYihaodian(){jQuery("#myYihaodian").mouseover(function(b){clearMyCartTopHeaderTimer();jQuery("#myYihaodianFloatDiv").show();b.stopPropagation()});jQuery("#myYihaodianFloatDiv").mouseout(function(b){clearMyCartTopHeaderTimer();myCartTopHeaderTimer=setTimeout(function(){jQuery("#myYihaodianFloatDiv").hide()},1000);b.stopPropagation()}).mouseover(function(b){clearMyCartTopHeaderTimer();b.stopPropagation()});jQuery("body").click(function(b){clearMyCartTopHeaderTimer();jQuery("#myYihaodianFloatDiv").hide();b.stopPropagation()})}var hasPingAnCookie=0;function writeHeaderContent(){YHDGLOBAL.getCookie(["ut","uname"],function(){var f=this["ut"];var e=this["uname"];var d=0;if(f){d=1}if(document.domain.indexOf("111.com",0)==-1){if(e){e=decodeURIComponent(e);if(e==null){e=""}jQuery("#user_name").text(e)
}}if(d==1){if(document.domain.indexOf("111.com",0)!=-1){if(e){e=decodeURIComponent(e);if(e==null){e=""}jQuery("#user_name").text(e)}}jQuery("#login").hide();jQuery("#logout").show()}YHDGLOBAL.getCookie(["ucocode","externaluserlevel"],function(){var h="";var c=this["ucocode"];var b=this["externaluserlevel"];if((c&&c=="pingan")){hasPingAnCookie=1;h="尊敬的万里通会员";jQuery(".provincebox").addClass("provincebox2")}else{if(c&&c=="tencent"){if(b&&b>0){h="尊敬的QQ会员"}else{if(b&&b==0){h="尊敬的QQ用户"}}}else{if(c&&c=="kaixin001"&&jQuery("#KX_JS_URL").size()>0){if(jQuery("#kx001_btn_login").parent().size()>0){jQuery("#logout").hide();jQuery("#kx001_btn_login").parent().show()}var a=jQuery("#KX_JS_URL").val();jQuery.getScript(a,function(){if(jQuery("#kx001_btn_login").size()>0&&jQuery("#kx001_btn_login").html()==""){try{KX001.init("974091834200c72a39a7bb394900fb0c","/pages/kaixin/kx001_receiver.html")
}catch(g){}}})}}}if(h&&h!=""){if(e==null){e=""}jQuery("#user_name").text(h+e)}})})}function pingan_quit(){var b=new Date((new Date()).getTime()).toGMTString();document.cookie="ut=;expires="+b+";domain=."+no3wUrl+";path=/";document.cookie="ucocode=;expires="+b+";domain=."+no3wUrl+";path=/";document.cookie="cocode=;expires="+b+";domain=."+no3wUrl+";path=/";location.href="https://www.wanlitong.com/eloyalty_chs/start.swe?SWENeedContext=false&SWECmd=Logoff&SWEC=2&SWEBID=-1&SWETS="}function kx001_onlogout(){window.location.href=httpUrl+"/passport/logoff.do"}function hightLightMenu(f,g){var e=jQuery(f);var h=location.href;e.each(function(b){if(b==0){return true}var c=jQuery(this).find("a");var d=c.attr("href");var i=c.attr("hl");var a=false;a=(h.indexOf(d)>-1);if(!a){if(i){a=(h.indexOf(i)>-1)}}if(!a){a=(h.indexOf("point2channel.do")>-1)&&(d.indexOf("/point2/pointIndex.do")>-1)
}if(a){if(b){e.eq(0).addClass("removehome");c.addClass("select")}return false}})}function initHeader(){jQuery(".top_bar_link > ul > li").hover(function(){jQuery(this).children("ul").show().end();jQuery(this).find(".qixia").addClass("qixia_hover")},function(){jQuery(this).children("ul").hide().end();jQuery(this).find(".qixia").removeClass("qixia_hover")});try{writeHeaderContent()}catch(b){}hightLightMenu("#global_menu li",null)}function lazyLoadBottomBrandsData(){var b=function(){var d=jQuery("#bottomBrand");if(!d.size()){return}var a=document.documentElement.clientHeight+Math.max(document.documentElement.scrollTop,document.body.scrollTop);if(d.offset().top>a+100||d.data("loaded")){return}else{d.data("loaded",true)}d.html("<p align='center'><img src='"+imagePath+"/loade.gif'/>请您稍等,数据正在加载...</p>");
jQuery.getJSON(URLPrefix.central+"/bottomBrand/ajaxGetBottomBrandsData.do?callback=?",function(c){d.html("");if(c.value){var g=jQuery(c.value).find("ul");var h="";g.each(function(){h+=jQuery(this).html()});d.html(h)}jQuery(window).unbind("scroll",b)})};jQuery(window).bind("scroll",b);b()}jQuery(document).ready(function(){if(isIndex!=1){initHeader()}lazyLoadBottomBrandsData()});jQuery(function(){if(jQuery.cookie("ut")){var p=0;var o=URLPrefix.central+"/top/myorder/ajaxInitShowMyOrderPageTotalData.do?timestame="+new Date()+"&callback=?";jQuery.getJSON(o,function(a){if(!(a&&a.resultCode)||a.resultCode==0){jQuery("#odrNumId").hide();jQuery("#tagPageOdrNumId").hide();jQuery("#idxOdrTipConId").removeClass("idxOdrTipCon").hide();jQuery(".top_bar_link>ul>li:first").data("isLogin",false);return false
}else{r(a)}});function s(b){var a=b.orderNum;var c=b.payReceiveNum;if(c==0){jQuery("#odrNumId").remove()}if(c+p>0){jQuery("#tagPageOdrNumId").html(c);jQuery("#odrNumId").addClass("odrNum").show();jQuery("#idxOdrTipConId").removeClass("idxOdrTipCon").hide();jQuery(".top_bar_link>ul>li:first").addClass("firstLi");jQuery(".top_bar_link>ul>li:first").hover(function(){if(new Date().getTime()-jQuery(".top_bar_link>ul>li:first").data("reqedtime")<=1*60*1000){return}if(jQuery(".top_bar_link>ul>li:first").data("inreq")){return}jQuery(".top_bar_link>ul>li:first").data("inreq",true);jQuery(".loading").show().siblings().hide();jQuery(".tabCon:gt(0)").children().not(".loading").hide();jQuery("#idxOdrTipConId").removeClass("idxOdrTipCon").hide();jQuery.getJSON(URLPrefix.central+"/top/myorder/ajaxShowMyOrderPageTagData.do?timestamp="+new Date()+"&callback=?",function(d){if(d.ERROR){jQuery(".top_bar_link>ul>li:first").data("inreq",false);
jQuery(".top_bar_link>ul>li:first").data("reqedtime",new Date().getTime());return}jQuery(".top_bar_link>ul>li:first").data("inreq",false);jQuery(".top_bar_link>ul>li:first").data("reqedtime",new Date().getTime());jQuery("#idxOdrTipConId").html(d.value);jQuery("#idxOdrTipConId").addClass("idxOdrTipCon").show();jQuery("#idxOdrTipConId ul li:eq(2)").attr("id","waitCommentId_"+p);t();q();jQuery(".loading").hide().siblings().show()})});jQuery("a[tk]").click(function(){var d=$(this),e=d.attr("tk");if(e){addTrackPositionToCookie("1",e)}})}}function m(){jQuery(".top_bar_link>ul>li:first,#idxOdrTipConId").hover(function(){jQuery(".top_bar_link>ul>li:first").addClass("myOdrHover");jQuery("#idxOdrTipConId").addClass("idxOdrTipCon").show();jQuery("#odrNumId").removeClass("odrNum").hide()},function(){jQuery(".top_bar_link>ul>li:first").removeClass("myOdrHover");
jQuery("#idxOdrTipConId").removeClass("idxOdrTipCon").hide();jQuery("#odrNumId").addClass("odrNum").show()})}function k(a){jQuery(a).addClass("cur").siblings().removeClass("cur");var b=jQuery(a).index();jQuery(".tabCon").eq(b).show().siblings(".tabCon").hide()}function q(){var c=jQuery("#idxOdrTipConId ul li:eq(0)").attr("id");var b=jQuery("#idxOdrTipConId ul li:eq(1)").attr("id");var a=jQuery("#idxOdrTipConId ul li:eq(2)").attr("id");var d=jQuery("#idxOdrTipConId ul li:eq(0)");if(c&&c.split("_")[1]>0){l(d)}else{if(b&&b.split("_")[1]>0){d=jQuery("#idxOdrTipConId ul li:eq(1)");l(d)}else{if(a&&a.split("_")[1]>0){d=jQuery("#idxOdrTipConId ul li:eq(2)");l(d)}else{jQuery(".top_bar_link>ul>li:first").removeClass("myOdrHover");jQuery("#idxOdrTipConId").removeClass("idxOdrTipCon").hide();jQuery("#odrNumId").remove();
return false}}}}function l(a){k(a);m();n()}function n(){jQuery(".idxOdrTipCon ul li").hover(function(){jQuery(this).addClass("cur").siblings().removeClass("cur");var a=jQuery(".idxOdrTipCon ul li").index(jQuery(".cur"));jQuery(".tabCon").eq(a).show().siblings(".tabCon").hide()})}function t(){if(p==0){jQuery("#idxOdrTipConId ul li:eq(2)").html("待评论("+0+")");jQuery("#pageTagUnCommentPId").html("您没有等待评论的订单")}else{jQuery("#idxOdrTipConId ul li:eq(2)").html("待评论("+p+")");jQuery("#pageTagUnCommentId").html(p)}}function r(a){jQuery.getJSON(URLPrefix.my+"/member/exp/getUserUnCommentNumJson.do?callback=?",function(b){if(b.ERROR){p=0}p=b.awaitComment;s(a)})}}});var yhdHead=yhdHead||{};yhdHead.topMenuImgLazyLoad=function(){jQuery("#global_menu li img").each(function(){jQuery(this).attr("src",function(){return jQuery(this).attr("original")}).removeAttr("original")})};yhdHead.newTopTabShow=function(b,a){if(b>a){jQuery("#global_menu li").each(function(c){if(c==a-1){jQuery(this).addClass("kf")}if(c>a-1){jQuery(this).remove()}})}};yhdHead.oldTopTabShow=function(b,a){if(b>a){jQuery("#global_menu span").each(function(c){if(c>a-1){jQuery(this).remove()}})}};yhdHead.dealWideNarrowScreen=function(){var b=screen.width>=1280;if(currSiteId==1){var a=jQuery("#global_menu li").length;var c=jQuery("#global_menu span").length;if(!b){yhdHead.newTopTabShow(a,10);yhdHead.oldTopTabShow(c,10)}else{if(isIndex){if(isIndex==1){yhdHead.newTopTabShow(a,10)}else{yhdHead.newTopTabShow(a,10)
}}else{yhdHead.newTopTabShow(a,10)}yhdHead.oldTopTabShow(c,10)}}else{var a=jQuery("#global_menu li").length;var c=jQuery("#global_menu span").length;if(!b){yhdHead.newTopTabShow(a,8);yhdHead.oldTopTabShow(c,6)}else{if(isIndex){if(isIndex==1){yhdHead.newTopTabShow(a,9)}else{yhdHead.newTopTabShow(a,8)}}else{yhdHead.newTopTabShow(a,8)}yhdHead.oldTopTabShow(c,6)}}};yhdHead.topMenuTrackInit=function(){jQuery("#wideScreenTabShowID li a[tk]").click(function(){var b=$(this),a=b.attr("tk");if(a){addTrackPositionToCookie("1",a)}});jQuery("#global_menu span a[tk]").click(function(){var b=$(this),a=b.attr("tk");if(a){addTrackPositionToCookie("1",a)}})};yhdHead.topRightPicAdv=function(){if(currSiteId==1){if(isIndex==1&&(screen.width>=1280)&&jQuery(".wrap").width()>980){jQuery("#headerTopRightAdId img").attr("src",function(){return jQuery(this).attr("original")
}).removeAttr("original");jQuery("#headerTopRightAdId").show()}}};jQuery(function(){yhdHead.dealWideNarrowScreen();yhdHead.topMenuImgLazyLoad();yhdHead.topRightPicAdv();yhdHead.topMenuTrackInit()});$(document).ready(function(){if(currSiteId&&currSiteId==1){jQuery("#targetMedical").attr("href","###").removeAttr("target").bind("click",medical_click);jQuery("#targetYw").attr("href","###").removeAttr("target").bind("click",yw_click);if(jQuery("#global_menu a[href='http://www.111.com.cn']").size()){jQuery("#global_menu a[href='http://www.111.com.cn']").attr("href","###").removeAttr("target").bind("click",yw_click)}else{jQuery("#global_menu a[href='http://www.111.com.cn/']").attr("href","###").removeAttr("target").bind("click",yw_click)}if(jQuery("#global_menu a[href='http://www.yiwang.cn']").size()){jQuery("#global_menu a[href='http://www.yiwang.cn']").attr("href","###").removeAttr("target").bind("click",medical_click)}else{jQuery("#global_menu a[href='http://www.yiwang.cn/']").attr("href","###").removeAttr("target").bind("click",medical_click)
}}if(currSiteId&&currSiteId==2){jQuery("#revertYhd").bind("click",yhd_click);if(jQuery("#global_menu a[href='http://www.yihaodian.com/']").size()){jQuery("#global_menu a[href='http://www.yihaodian.com/']").attr("href","###").removeAttr("target").bind("click",yhd_click)}else{jQuery("#global_menu a[href='http://www.yihaodian.com']").attr("href","###").removeAttr("target").bind("click",yhd_click)}}});function checkLogin_for_auth(){if(getCookie_for_auth("ut")==""){return false}else{return true}}function getCookie_for_auth(f){var e=document.cookie.split(";");for(var g=0;g<e.length;g++){var h=e[g].split("=");if(h[0].replace(/(^\s*)|(\s*$)/g,"")==f){return h[1]}}return""}function medical_click(){var e="target_site=2";var h="https://passport.yihaodian.com/auth/redirect.do?"+e+"&callback=?";var f="http://www.yiwang.cn/";
if(!checkLogin_for_auth()){window.location.href=f;return false}try{jQuery.getJSON(h,function(a){if(a){if(a.is_yhd_login==0){}if(a.is_yhd_login==1){f=a.target_url}window.location.href=f}})}catch(g){window.location.href=f}}function yw_click(){var e="target_site=1";var h="https://passport.yihaodian.com/auth/redirect.do?"+e+"&callback=?";var f="http://www.111.com.cn/";if(!checkLogin_for_auth()){window.location.href=f;return false}try{jQuery.getJSON(h,function(a){if(a){if(a.is_yhd_login==0){}if(a.is_yhd_login==1){f=a.target_url}window.location.href=f}})}catch(g){window.location.href=f}}function yhd_click(){var d="https://passport.111.com.cn/auth/revertToYhd.do?callback=?";var e="http://www.yihaodian.com";if(!checkLogin_for_auth()){window.location.href=e;return false}try{jQuery.getJSON(d,function(a){if(a){e=a.target_url;
window.location.href=e}})}catch(f){window.location.href=e}};var returnUrl=document.location.href;var yhdPublicLogin=yhdPublicLogin||{};yhdPublicLogin.checkLogin=function(){if(yhdPublicLogin.getCookie("ut")){return true}else{return false}};yhdPublicLogin.getCookie=function(c){var d=document.cookie.split(";");for(var b=0;b<d.length;b++){var a=d[b].split("=");if(a[0].replace(/(^\s*)|(\s*$)/g,"")==c){return a[1]}}return""};yhdPublicLogin.loadCssAndJs=function(c,a){var b="";if(a=="js"){b=document.createElement("script");b.setAttribute("type","text/javascript");b.setAttribute("src",c)}else{if(a=="css"){b=document.createElement("link");b.setAttribute("rel","stylesheet");b.setAttribute("type","text/css");b.setAttribute("href",c)}}if(typeof b!="undefined"){document.getElementsByTagName("head")[0].appendChild(b)}};yhdPublicLogin.showLoginDiv=function(b,a){if(a&&yhdPublicLogin.checkLogin()){return
}passportLoginFrame(URLPrefix.passport,null,function(g){try{if(b){var d="";if(b.toLowerCase().indexOf("http")<0){var h=window.location.protocol;var f=window.location.host;var c=h+"//"+f;d=c}var i=d+b;window.location.href=i}else{window.location.reload(true)}}catch(j){}})};yhdPublicLogin.showLoginDivNone=function(c,d,b,a){if(d&&yhdPublicLogin.checkLogin()){return}passportLoginFrame(c,b,a)};yhdPublicLogin.showTopLoginInfo=function(){try{YHDGLOBAL.getCookie(["ut","uname"],function(){var d=this["ut"];var b=this["uname"];var c=0;if(d){c=1}if(c==1){if(b){b=decodeURIComponent(b);if(b==null){b=""}jQuery("#user_name").text(b)}jQuery("#login").hide();jQuery("#logout").show()}})}catch(a){}};

jQuery(function(){
	var a="";
	if(URLPrefix&&URLPrefix.statics){
		a=URLPrefix.statics
	}else{
		if(currSiteId&&currSiteId==2){
			a="http://image.111.com.cn/statics"
         }else{
        	  a="http://image.yihaodianimg.com/statics"
        }
	}
	yhdPublicLogin.loadCssAndJs(a+"/global/css/global_yhdLib.css","css");
	yhdPublicLogin.loadCssAndJs(a+"/global/js/global_yhdLib.js","js");
	yhdPublicLogin.loadCssAndJs(a+"/global/js/global_login_frame.js","js")
});