!function($){
 /* 基于Bootstrap Typeahead改造而来的自动完成插件
  * Author：F.L.F
  * Site: http://digdata.me
  * 2013-08-21 增加树形结构支持 凌郁林
  * ================================= */

  var Autocomplete = function (element, options) {
    this.$element = $(element)
    this.options = $.extend({}, $.fn.autocomplete.defaults, options)
    this.sorter = this.options.sorter || this.sorter
    this.highlighter = this.options.highlighter || this.highlighter
    this.updater = this.options.updater || this.updater
    this.source = this.options.source
    this.$menu = $(this.options.menu)
    this.shown = false
	this.formatItem = this.options.formatItem || this.formatItem
	this.setValue = this.options.setValue || this.setValue
    this.listen()
  }

  Autocomplete.prototype = {

    constructor: Autocomplete
  , processObj:0
  , formatItem:function(item){
		return item.toString();
	}
  , setValue:function(item){
		return {"data-value":item.toString(),"real-value":item.toString(),"real-value2":item.toString(),"real-value3":item.toString()};
    }
  
  , select: function () {
      var val = this.$menu.find('.active').attr('data-value');
	  	var realVal = this.$menu.find('.active').attr('real-value');
	  	var realVal2 = this.$menu.find('.active').attr('real-value2');
	  	var realVal3 = this.$menu.find('.active').attr('real-value3');
      this.$element
        .val(this.updater(val)).attr("real-value",realVal).attr("real-value2",realVal2).attr("real-value2",realVal3)
        .change();
      return this.hide();
    }

  , updater: function (item) {
      return item;
    }

  , show: function () {
      var pos = $.extend({}, this.$element.position(), {
        height: this.$element[0].offsetHeight
      });
	 
      this.$menu
        .insertAfter(this.$element)
        .css({
          top: pos.top + pos.height
        , left: pos.left
		, "min-width":this.$element.outerWidth()-2
        })
        .show();

      this.shown = true;
      return this;
    }

  , hide: function () {
      this.$menu.hide();
      this.shown = false;
      return this;
    }

  , lookup: function (event) {
      var items;

      this.query = this.$element.val();

      if (!this.query || this.query.length < this.options.minLength) {
        return this.shown ? this.hide() : this;
      }

      items = $.isFunction(this.source) ? this.source(this.query, $.proxy(this.process, this)) : this.source;

      return items ? this.process(items) : this;
    }

  , process: function (items) {
      var that = this
      if (!items.length) {
        return this.shown ? this.hide() : this
      }

      return this.render(items.slice(0, this.options.items)).show()
    }
 
  , highlighter: function (item) {
	  var that = this
	  item = that.formatItem(item)
      var query = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, '\\$&')
      return item.replace(new RegExp('(' + query + ')', 'ig'), function ($1, match) {
        return '<strong style="color:#FF6600;">' + match + '</strong>'
      })
    }

  , render: function (items) {
      var that = this;

      /*items = $(items).map(function (i, item) {
        i = $(that.options.item).attr(that.setValue(item))
        i.find('a').html(that.highlighter(item));
        var tmp = i[0];
        return tmp;
      })
      items.first().addClass('active')*/
      //var tree = array2tree(items);
      var tree = items;
      var arr = [];
      function sortArr(_array){
    	  _array.sort(function(a,b){
    		  if(that.formatItem(a).indexOf(that.query) >= 0)
    			  return 1;
    		  else 
    			  return -1;
    	  });
    	  if(that.options.childrenField){
	    	  for(var i in _array){
	    		  var children = _array[i][that.options.childrenField];
	    		  if(children)
	    			  sortArr(children);
	    	  }
    	  }
      }
      sortArr(tree);
      function addToArr(item,level){
    	  var $item_htm = $(that.options.item).attr(that.setValue(item));
    	  $item_htm.find('a').html(that.highlighter(item));
    	  if(level == 0)
    		  arr.push($item_htm[0]);
    	  if(that.options.childrenField){
	    	  var children = item[that.options.childrenField];
	    	  if(children && children.length > 0){	
	    		  var $ul = $item_htm.addClass('dropdown-submenu').append("<ul class='dropdown-menu'></ul>").find('ul');
	    		  for(var i in children){
	    			  var $li = addToArr(children[i],level + 1);
	    			  $ul.append($li);
	    		  }
	    	  }
    	  }
    	  return $item_htm;
      }
      for(var i in tree){
    	  var item = tree[i];
    	  addToArr(item,0);
      }
      if(arr.length > 0)
    	  $(arr[0]).addClass('active');
      items = arr;
      

      
      this.$menu.html(items)
      return this
    }

  , next: function (event) {
      var active = this.$menu.find('.active').removeClass('active')
        , next = active.next()

      if (!next.length) {
        next = $(this.$menu.find('li')[0])
      }

      next.addClass('active')
    }

  , prev: function (event) {
      var active = this.$menu.find('.active').removeClass('active')
        , prev = active.prev()

      if (!prev.length) {
        prev = this.$menu.find('li').last()
      }

      prev.addClass('active')
    }

  , listen: function () {
      this.$element
        .on('focus',    $.proxy(this.focus, this))
        .on('blur',     $.proxy(this.blur, this))
        .on('keypress', $.proxy(this.keypress, this))
        .on('keyup',    $.proxy(this.keyup, this))

      if (this.eventSupported('keydown')) {
        this.$element.on('keydown', $.proxy(this.keydown, this))
      }

      this.$menu
        .on('click','li', $.proxy(this.click, this))
        .on('mouseenter', $.proxy(this.mouseenter, this))
        .on('mouseleave', $.proxy(this.mouseleave, this))
		.on('mousemove', 'li', $.proxy(this.mousemove, this))
    }

  , eventSupported: function(eventName) {
      var isSupported = eventName in this.$element
      if (!isSupported) {
        this.$element.setAttribute(eventName, 'return;')
        isSupported = typeof this.$element[eventName] === 'function'
      }
      return isSupported
    }

  , move: function (e) {
      if (!this.shown) return

      switch(e.keyCode) {
        case 9: // tab
        case 13: // enter
        case 27: // escape
          e.preventDefault()
          break

        case 38: // up arrow
          e.preventDefault()
          this.prev()
          break

        case 40: // down arrow
          e.preventDefault()
          this.next()
          break
      }

      e.stopPropagation()
    }

  , keydown: function (e) {
      this.suppressKeyPressRepeat = ~$.inArray(e.keyCode, [40,38,9,13,27])
      this.move(e)
    }

  , keypress: function (e) {
      if (this.suppressKeyPressRepeat) return
      this.move(e)
    }

  , keyup: function (e) {
      switch(e.keyCode) {
        case 40: // down arrow
        case 38: // up arrow
        case 16: // shift
        case 17: // ctrl
        case 18: // alt
          break

        case 9: // tab
        case 13: // enter
          if (!this.shown) return
          this.select()
          break

        case 27: // escape
          if (!this.shown) return
          this.hide()
          break

        default:
		  var that = this
		  if(that.processObj){
		    clearTimeout(that.processObj)
			that.processObj = 0
		  }
		  that.processObj = setTimeout(function(){
			that.lookup()
		  },that.options.delay)
      }

      e.stopPropagation()
      e.preventDefault()
  }

  , focus: function (e) {
      this.focused = true
    }

  , blur: function (e) {
      this.focused = false
      if (!this.mousedover && this.shown) this.hide()
    }

  , click: function (e) {
      e.stopPropagation()
      e.preventDefault()
      this.select()
      this.$element.focus()
    }

  , mouseenter: function (e) {
      this.mousedover = true      
    }
	
  ,mousemove:function(e){
	  this.$menu.find('.active').removeClass('active')
      $(e.currentTarget).addClass('active')
	}
	
  , mouseleave: function (e) {
      this.mousedover = false;
      if (!this.focused == false && this.shown){
    	  this.hide();//该代码会引发鼠标经过下拉消失BUG--凌郁林2014-03-26注
      }
    }

  }


  /* TYPEAHEAD PLUGIN DEFINITION
   * =========================== */

  var old = $.fn.autocomplete

  $.fn.autocomplete = function (option) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('autocomplete')
        , options = typeof option == 'object' && option
      if (!data) $this.data('autocomplete', (data = new Autocomplete(this, options)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.autocomplete.defaults = {
    source: []
  , items: 8
  , menu: '<ul class="typeahead dropdown-menu"></ul>'
  , item: '<li style="display:inline"><a href="#"></a></li>'
  , minLength: 1
  , delay: 500
  }

  $.fn.autocomplete.Constructor = Autocomplete


 /* TYPEAHEAD NO CONFLICT
  * =================== */

  $.fn.autocomplete.noConflict = function () {
    $.fn.autocomplete = old
    return this
  }


 /* TYPEAHEAD DATA-API
  * ================== */

  $(document).on('focus.autocomplete.data-api', '[data-provide="autocomplete"]', function (e) {
    var $this = $(this)
    if ($this.data('autocomplete')) return
    $this.autocomplete($this.data())
  })

}(window.jQuery);