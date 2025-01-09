// Object to store all charts for easy management
const charts = {};

// Universal function to create a chart
function createChart(chartId, labels, dataValues, labelText, type, options = {}) {
    const ctx = document.getElementById(chartId).getContext('2d');

    // If the chart already exists, destroy it to avoid errors
    if (charts[chartId]) {
        charts[chartId].destroy();
    }

    // Default chart options
    const defaultOptions = {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            tooltip: {
                callbacks: {
                    label: function (tooltipItem) {
                        return tooltipItem.raw + ' USD'; // Format tooltips to show values
                    }
                }
            },
            legend: {
                position: 'top',
                labels: {
                    font: {
                        size: 14
                    }
                }
            }
        }
    };

    // Set colors depending on chart type
    let backgroundColor, borderColor;

    if (type === 'pie') {
        backgroundColor = [
            'rgba(135, 206, 235, 0.2)',
            'rgba(144, 238, 144, 0.2)',
            'rgba(255, 228, 196, 0.2)',
            'rgba(211, 211, 211, 0.2)',
            'rgba(255, 239, 189, 0.2)' 
        ];
        borderColor = 'rgba(75, 192, 192, 1)';
    } else if (type === 'bar') {
        backgroundColor = 'rgba(75, 192, 192, 0.2)';
        borderColor = 'rgba(75, 192, 192, 1)';
    }

    // Merge default options with the provided options
    options = Object.assign({}, defaultOptions, options);

    // Create and save the new chart in the `charts` object
    charts[chartId] = new Chart(ctx, {
        type: type,
        data: {
            labels: labels,
            datasets: [{
                label: labelText,
                data: dataValues,
                backgroundColor: backgroundColor,
                borderColor: borderColor,
                borderWidth: 1
            }]
        },
        options: options
    });

    return charts[chartId];
}

// Universal function to sort chart data
function sortChartData(chartId, sortType) {
    // Get the chart by `chartId`
    const chart = charts[chartId];
    if (!chart) return;

    // Copy chart data
    const labels = chart.data.labels.slice();
    const dataValues = chart.data.datasets[0].data.slice();

    // Sort data based on `sortType`
    const sortedData = labels.map((label, index) => ({
        label: label,
        data: dataValues[index]
    }));

    if (sortType === 'asc') {
        sortedData.sort((a, b) => a.data - b.data);
    } else if (sortType === 'desc') {
        sortedData.sort((a, b) => b.data - a.data);
    }

    // Update chart data
    chart.data.labels = sortedData.map(item => item.label);
    chart.data.datasets[0].data = sortedData.map(item => item.data);

    // Refresh the chart
    chart.update();
}



let doughnutChart = null;  // Global variable to store chart instance

// Function to create 
function createDoughnutChart(salesPersonProfits) {
    console.log("salesPersonProfits in func js:", salesPersonProfits);
    let value = null;
    let max = null;
    let remaining = null;

    // Get selected salesperson from the dropdown
    const selectedSalesPerson = document.getElementById("salesPersonSelect").value;

    if (!selectedSalesPerson) {
        return;
    }

    // Find the corresponding value for the selected salesperson
    const selectedProfit = salesPersonProfits[selectedSalesPerson];

    if (selectedProfit !== undefined) {
        value = parseFloat(selectedProfit);
    } else {
        console.log("Salesperson not found.");
        return;
    }

    // Find the maximum profit
    const maxProfitRecord = Object.entries(salesPersonProfits).reduce((max, current) => {
        return parseFloat(current[1]) > parseFloat(max[1]) ? current : max;
    });
    max = parseFloat(maxProfitRecord[1]);

    // Calculate remaining profit difference
    remaining = max - value;

    // Destroy the previous chart if it exists
    if (doughnutChart) {
        doughnutChart.destroy();
    }

    console.log("value: ", value);
    console.log("max: ", max);
    console.log("remaining: ", remaining);

    // Prepare the context for the chart
    const ctx = document.getElementById('doughnutChart').getContext('2d');

    // Create the chart
    doughnutChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            datasets: [{
                data: [value, remaining], // Dynamic data: value, remaining
                backgroundColor: ['rgba(75, 192, 192, 0.5)', 'rgba(75, 192, 192, 0.2)'],
                borderWidth: 0,
            }]
        },
        options: {
            responsive: true,
            cutout: '80%',
            rotation: -90,
            circumference: 180,
            plugins: {
                tooltip: {
                    enabled: false, // Disable tooltips
                },
                legend: {
                    display: false, // Show the legend
                },
                title: {
                    display: false, // Optional title
                },
                // Center text using the built-in Chart.js plugin
                centerText: {
                    display: true,
                    text: `${value} (${Math.round((value / max) * 100)}%)`,
                }
            }
        },
        plugins: [{
            id: 'centerText',
            beforeDraw(chart) {
                const { width, height, ctx } = chart;
                const fraction = value / max;
                const percentage = Math.round((fraction) * 100) + '%';

                // Draw central text
                ctx.save();
                ctx.font = '16px Arial';
                ctx.textAlign = 'center';
                ctx.fillStyle = 'black';
                ctx.fillText(`${value} (${percentage})`, width / 2, height / 1.8);
                ctx.restore();

                // Draw the tick marks and axis labels
                const step = max / 5; // Interval between labels
                const radius = width / 2 * 0.6; // Position slightly outside the chart
                ctx.font = '12px Arial';
                ctx.textAlign = 'center';
                ctx.fillStyle = 'black';
                ctx.strokeStyle = 'black';

                const tickRadius = radius + 20;
                const tickLenght = 20;

                for (let i = 0; i <= max; i += step) {
                    const angle = (180 + (180 * (i / max))) * (Math.PI / 180); // Calculate the angle
                    const x = width / 2 + (radius) * Math.cos(angle); // X-coordinate
                    const y = height - 100 + (radius) * Math.sin(angle); // Y-coordinate

                    // Draw the label
                    ctx.fillText(i.toString(), x, y);

                    // Draw the tick mark
                    const tickX1 = width / 2 + (tickRadius) * Math.cos(angle);
                    const tickY1 = height - 100 + (tickRadius) * Math.sin(angle);
                    const tickX2 = width / 2 + (tickRadius + tickLenght) * Math.cos(angle);
                    const tickY2 = height - 100 + (tickRadius + tickLenght) * Math.sin(angle);

                    ctx.beginPath();
                    ctx.moveTo(tickX1, tickY1);
                    ctx.lineTo(tickX2, tickY2);
                    ctx.stroke();
                }

                // Draw the filled circle
                const circleX = width / 2;
                const circleY = height - 100;

                ctx.beginPath();  // Start a new path
                ctx.arc(circleX, circleY, 10, 0, 2 * Math.PI);  // Draw a full circle (0 to 2*PI is full circle)
                ctx.fillStyle = 'rgba(75, 192, 192, 1)';  // Set the fill color
                ctx.fill();  // Fill the circle with the current fill style

                // Draw the filled triangle
                const angle = (180 + (180 * (value / max))) * (Math.PI / 180);
                const triangleBase = 20;
                const triangleHeight = 80;
                const tr1stX = circleX + (triangleHeight) * Math.cos(angle);
                const tr1stY = circleY + (triangleHeight) * Math.sin(angle);
                const tr2stX = circleX + (triangleBase / 2) * Math.cos(angle + Math.PI / 2);
                const tr2stY = circleY + (triangleBase / 2) * Math.sin(angle + Math.PI / 2);
                const tr3stX = circleX + (triangleBase / 2) * Math.cos(angle - Math.PI / 2);
                const tr3stY = circleY + (triangleBase / 2) * Math.sin(angle - Math.PI / 2);

                ctx.beginPath();
                ctx.moveTo(tr1stX, tr1stY);
                ctx.lineTo(tr2stX, tr2stY);
                ctx.lineTo(tr3stX, tr3stY);
                ctx.closePath();
                ctx.fillStyle = 'rgba(75, 192, 192, 1)';
                ctx.fill();
            }
        }]
    });
}