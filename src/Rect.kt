class Rect(var x: Int, var y: Int, var width: Int, var height: Int) : Figure(0), Movable, Transforming {
    var color: Int = -1

    lateinit var name: String
    constructor(rect: Rect) : this(rect.x, rect.y, rect.width, rect.height)

    override fun area(): Float {
        return (width * height).toFloat()
    }

    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
    }

    override fun resize(zoom: Int) {
        width *= zoom
        height *= zoom
    }
    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        var dx = 0
        var dy = 0
        if (centerX == x && centerY == y) return
        else if (direction == RotateDirection.Clockwise) {
            dx = y - centerY
            dy = centerX - x
        }
        else if (direction == RotateDirection.CounterClockwise) {
            dx = centerY - y
            dy = x - centerX
        }
        x = dx + centerX
        y = dy + centerY
        val buf = width
        width = height
        height = buf
    }

    override fun toString(): String {
        return "x: $x, y: $y, width: $width, height: $height"
    }
}