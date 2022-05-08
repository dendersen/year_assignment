let list = [];
let total = 20;
function setup() {
  createCanvas(windowWidth, windowHeight);
  background(220);
  for (let i = 0; i < total; i++) {
    list.push(new card(i));
  }
}

function draw() {
  for (let i = 0; i < 20; i++) {
    list[i].show([10, 11, 12, 13]);
  }
}

class card {
  constructor(id) {
    this.id = id;
    this.total;
    this.w = 100;
    this.h = 200;
    this.x = 50;
    this.y = 100;
  }

  show(range) {
    if (!range.contains(this.id)) {
      return;
    }
    fill(255);
    rect(this.x + range.indexOf(this.id) * 75, this.y, this.w, this.h);
    rect(this.x + 25 + range.indexOf(this.id) * 75, this.y + 50, 50, 50);
  }
}
